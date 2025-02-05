package com.paresh.practice.file;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Example of a parallel file downloader that downloads a file in parallel using multiple threads.
 * The file is split into chunks, each downloaded by a separate thread, and then merged into the final file.
 * The number of threads, max retries, and retry delay can be adjusted based on the network conditions.
 * The program can be run from the command line with the following arguments:
 * java ParallelFileDownloader <file-url> <destination-file-name> [<max-retries> <retry-delay-ms> <retry-whole-file>]
 * java ParallelFileDownloader https://www.apache.org/dyn/closer.cgi?filename=activemq/activemq-artemis/2.39.0/apache-artemis-2.39.0-bin.zip&action=download mq1.zip 2 5 true
 *
 */
public class ParallelFileDownloader {
    private static final int NUM_THREADS = 4; // Number of parallel threads (can be adjusted based on your network)
    private static int MAX_RETRIES = 3; // Default number of retries per chunk
    private static int RETRY_DELAY_MS = 5000; // Delay in milliseconds between retries
    private static final String TEMP_SUFFIX = ".part"; // Suffix for temporary part files

    public static void main(String[] args) {
        // Check if at least the file URL and destination file name are provided
        if (args.length < 2) {
            System.out.println("Usage: java ParallelFileDownloader <file-url> <destination-file-name> [<max-retries> <retry-delay-ms> <retry-whole-file>]");
            return;
        }

        String fileUrl = args[0];  // File URL (web URL, file URL, or local file path)
        String destinationFile = args[1];  // Destination file name

        // Parse optional command-line arguments for retries, delay, and retry flag
        try {
            if (args.length >= 3) {
                MAX_RETRIES = Integer.parseInt(args[2]);  // Max retries per chunk
            }
            if (args.length >= 4) {
                RETRY_DELAY_MS = Integer.parseInt(args[3]);  // Retry delay in milliseconds
            }
            boolean retryEntireFile = args.length >= 5 && Boolean.parseBoolean(args[4]);  // Flag to retry whole file
            processDownload(fileUrl, destinationFile, retryEntireFile);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments for retries or delay, using default values.");
            processDownload(fileUrl, destinationFile, false);
        }
    }

    // Method to handle the download process
    private static void processDownload(String fileUrl, String destinationFile, boolean retryEntireFile) {
        try {
            // Get the file size from the server or local file
            long fileSize = getFileSize(fileUrl);
            if (fileSize <= 0) {
                System.out.println("Failed to get the file size.");
                return;
            }

            System.out.println("File size: " + fileSize + " bytes.");

            // Calculate the size of each chunk
            long chunkSize = fileSize / NUM_THREADS;

            // Check if the file already exists and if it needs to be completed or retried
            if (new File(destinationFile).exists() && !retryEntireFile) {
                System.out.println("File already exists. Checking for missing or incomplete parts...");
                verifyAndResumeDownload(fileUrl, destinationFile, fileSize, chunkSize);
            } else {
                // Start a fresh download if the file doesn't exist or if the entire download is to be retried
                System.out.println("Starting fresh download...");
                startDownload(fileUrl, destinationFile, fileSize, chunkSize);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get the size of the file from the server or local file
    private static long getFileSize(String fileUrl) throws IOException {
        if (fileUrl.startsWith("http://") || fileUrl.startsWith("https://")) {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            return connection.getContentLengthLong();
        } else if (fileUrl.startsWith("file://")) {
            // Handle file URL (file://)
            URI uri = URI.create(fileUrl);
            File file = new File(uri);
            return file.length();
        } else {
            // Local file path (assumes it's a valid local path)
            File file = new File(fileUrl);
            if (file.exists()) {
                return file.length();
            } else {
                throw new IOException("File not found: " + fileUrl);
            }
        }
    }

    // Method to start downloading the file from scratch
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

    // Method to verify and resume download
    private static void verifyAndResumeDownload(String fileUrl, String destinationFile, long fileSize, long chunkSize) throws InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<DownloadTask> tasks = createDownloadTasks(fileUrl, destinationFile, fileSize, chunkSize);

        // Check if any part is missing or incomplete
        List<DownloadTask> retryTasks = new ArrayList<>();
        for (DownloadTask task : tasks) {
            if (!isChunkDownloaded(destinationFile, task.partIndex, chunkSize)) {
                retryTasks.add(task); // Add only the tasks for missing or incomplete parts
            }
        }

        // If there are missing or incomplete parts, retry them
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

    // Method to check if a specific chunk is downloaded
    private static boolean isChunkDownloaded(String destinationFile, int partIndex, long chunkSize) {
        File partFile = new File(destinationFile + TEMP_SUFFIX + partIndex);
        return partFile.exists() && partFile.length() == chunkSize;
    }

    // Method to create download tasks
    private static List<DownloadTask> createDownloadTasks(String fileUrl, String destinationFile, long fileSize, long chunkSize) {
        List<DownloadTask> tasks = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            long startByte = i * chunkSize;
            long endByte = (i == NUM_THREADS - 1) ? fileSize - 1 : (startByte + chunkSize - 1);
            tasks.add(new DownloadTask(fileUrl, destinationFile, startByte, endByte, i));
        }
        return tasks;
    }

    // Method to merge the downloaded chunks into the final file
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
                chunkFile.delete();  // Delete the part file after merging
            }
        }
    }

    // DownloadTask class that represents the download of a chunk
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

        // Method to download a chunk with retry logic
        private void downloadChunkWithRetry(String fileUrl, String destinationFile, long startByte, long endByte, int partIndex) throws IOException {
            int retries = 0;
            boolean success = false;

            while (retries < MAX_RETRIES && !success) {
                try {
                    downloadChunk(fileUrl, destinationFile, startByte, endByte, partIndex);
                    success = true;  // If download is successful, exit the loop
                } catch (IOException e) {
                    retries++;
                    System.out.println("Failed to download part " + partIndex + ", retrying (" + retries + "/" + MAX_RETRIES + ")...");
                    if (retries >= MAX_RETRIES) {
                        throw new IOException("Max retries reached for part " + partIndex);
                    }
                    try {
                        Thread.sleep(RETRY_DELAY_MS);  // Wait before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Download interrupted for part " + partIndex);
                    }
                }
            }
        }

        // Method to download a chunk of the file
        private void downloadChunk(String fileUrl, String destinationFile, long startByte, long endByte, int partIndex) throws IOException {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
            connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte); // Range header for partial download
            connection.connect();

            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
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
