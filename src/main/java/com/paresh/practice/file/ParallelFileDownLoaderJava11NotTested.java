package com.paresh.practice.file;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpHeaders;
import java.util.*;
import java.util.concurrent.*;

public class ParallelFileDownLoaderJava11NotTested {
    private static final int NUM_THREADS = 4;  // Number of parallel threads (adjustable)
    private static int MAX_RETRIES = 3;        // Default retries per chunk
    private static int RETRY_DELAY_MS = 5000;  // Delay between retries in ms
    private static final String TEMP_SUFFIX = ".part";  // Suffix for temporary part files

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        long startTimeSeconds = System.nanoTime();

        if (args.length < 2) {
            System.out.println("Usage: java ParallelFileDownloader <file-url> <destination-file-name> [<max-retries> <retry-delay-ms> <retry-whole-file>]");
            return;
        }

        String fileUrl = args[0];  // File URL
        String destinationFileName = new File(new URL(fileUrl).getPath()).getName();  // Extract destination file name
        String downloadsFolder = System.getProperty("user.home") + File.separator + "Downloads";
        String destinationFile = downloadsFolder + File.separator + destinationFileName;

        try {
            if (args.length >= 3) {
                MAX_RETRIES = Integer.parseInt(args[1]);  // Max retries per chunk
            }
            if (args.length >= 4) {
                RETRY_DELAY_MS = Integer.parseInt(args[2]);  // Retry delay in milliseconds
            }
            boolean retryEntireFile = args.length >= 4 && Boolean.parseBoolean(args[3]);  // Retry flag
            processDownload(fileUrl, destinationFile, retryEntireFile);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments for retries or delay, using default values.");
            processDownload(fileUrl, destinationFile, false);
        }

        long endTime = System.currentTimeMillis();
        long endTimeSeconds = System.nanoTime();
        long duration = endTime - startTime;
        long durationSeconds = endTimeSeconds - startTimeSeconds;
        System.out.println("Total time taken: " + duration + " ms");
        System.out.println("Total time taken in seconds: " + durationSeconds / 1_000_000_000 + " s");
    }

    private static void processDownload(String fileUrl, String destinationFile, boolean retryEntireFile) {
        try {
            long fileSize = getFileSize(fileUrl);
            if (fileSize <= 0) {
                System.out.println("Failed to get the file size.");
                return;
            }
            System.out.println("File size: " + fileSize + " bytes (" + (fileSize / (1024 * 1024)) + " MB).");

            long chunkSize = fileSize / NUM_THREADS;
            if (new File(destinationFile).exists() && !retryEntireFile) {
                System.out.println("File already exists. Checking for missing or incomplete parts...");
                verifyAndResumeDownload(fileUrl, destinationFile, fileSize, chunkSize);
            } else {
                System.out.println("Starting fresh download...");
                startDownload(fileUrl, destinationFile, fileSize, chunkSize);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long getFileSize(String fileUrl) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fileUrl))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

        return Long.parseLong(response.headers().firstValue("Content-Length").orElseThrow());
    }

    private static void startDownload(String fileUrl, String destinationFile, long fileSize, long chunkSize) throws InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<DownloadTask> tasks = createDownloadTasks(fileUrl, destinationFile, fileSize, chunkSize);

        List<Future<Void>> futures = executorService.invokeAll(tasks);

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        mergeChunks(destinationFile, NUM_THREADS);
        executorService.shutdown();
        System.out.println("Download and merging completed.");
    }

    private static void verifyAndResumeDownload(String fileUrl, String destinationFile, long fileSize, long chunkSize) throws InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<DownloadTask> tasks = createDownloadTasks(fileUrl, destinationFile, fileSize, chunkSize);

        List<DownloadTask> retryTasks = new ArrayList<>();
        for (DownloadTask task : tasks) {
            if (!isChunkDownloaded(destinationFile, task.partIndex, chunkSize)) {
                retryTasks.add(task);
            }
        }

        if (!retryTasks.isEmpty()) {
            System.out.println("Retrying missing parts...");
            List<Future<Void>> futures = executorService.invokeAll(retryTasks);
            for (Future<Void> future : futures) {
                try {
                    future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            mergeChunks(destinationFile, NUM_THREADS);
        } else {
            System.out.println("All parts are already downloaded.");
        }

        executorService.shutdown();
    }

    private static boolean isChunkDownloaded(String destinationFile, int partIndex, long chunkSize) {
        File partFile = new File(destinationFile + TEMP_SUFFIX + partIndex);
        return partFile.exists() && partFile.length() == chunkSize;
    }

    private static List<DownloadTask> createDownloadTasks(String fileUrl, String destinationFile, long fileSize, long chunkSize) {
        List<DownloadTask> tasks = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            long startByte = i * chunkSize;
            long endByte = (i == NUM_THREADS - 1) ? fileSize - 1 : (startByte + chunkSize - 1);
            tasks.add(new DownloadTask(fileUrl, destinationFile, startByte, endByte, i));
        }
        return tasks;
    }

    private static void mergeChunks(String destinationFile, int numChunks) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile))) {
            for (int i = 0; i < numChunks; i++) {
                File chunkFile = new File(destinationFile + TEMP_SUFFIX + i);
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(chunkFile))) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                chunkFile.delete();
            }
        }
    }

    static class DownloadTask implements Callable<Void> {
        private String fileUrl;
        private String destinationFile;
        private long startByte;
        private long endByte;
        private int partIndex;

        public DownloadTask(String fileUrl, String destinationFile, long startByte, long endByte, int partIndex) {
            this.fileUrl = fileUrl;
            this.destinationFile = destinationFile;
            this.startByte = startByte;
            this.endByte = endByte;
            this.partIndex = partIndex;
        }

        @Override
        public Void call() throws Exception {
            downloadChunkWithRetry(fileUrl, destinationFile, startByte, endByte, partIndex);
            return null;
        }

        private void downloadChunkWithRetry(String fileUrl, String destinationFile, long startByte, long endByte, int partIndex) throws IOException {
            int retries = 0;
            boolean success = false;

            while (retries < MAX_RETRIES && !success) {
                try {
                    downloadChunk(fileUrl, destinationFile, startByte, endByte, partIndex);
                    success = true;
                } catch (IOException e) {
                    retries++;
                    System.out.println("Failed to download part " + partIndex + ", retrying (" + retries + "/" + MAX_RETRIES + ")...");
                    if (retries >= MAX_RETRIES) {
                        throw new IOException("Max retries reached for part " + partIndex);
                    }
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Download interrupted for part " + partIndex);
                    }
                }
            }
        }

        private void downloadChunk(String fileUrl, String destinationFile, long startByte, long endByte, int partIndex) throws IOException {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fileUrl))
                    .header("Range", "bytes=" + startByte + "-" + endByte) // Request only a specific range
                    .build();

            HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            try (BufferedInputStream inputStream = new BufferedInputStream(response.body());
                 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile + TEMP_SUFFIX + partIndex))) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Part " + partIndex + " downloaded.");
        }
    }
}
