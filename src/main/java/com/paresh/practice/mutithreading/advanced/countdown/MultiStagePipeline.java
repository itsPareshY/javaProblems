package com.paresh.practice.mutithreading.advanced.countdown;

import java.util.concurrent.CountDownLatch;

public class MultiStagePipeline {
    public static void main(String[] args) throws InterruptedException {

        // Stage 1: Worker 1 and Worker 2
        CountDownLatch stage1Latch = new CountDownLatch(2);

        // Stage 2: Worker 3
        CountDownLatch stage2Latch = new CountDownLatch(1);

        // Stage 3: Worker 4
        CountDownLatch stage3Latch = new CountDownLatch(1);

        // Worker 1
        Thread worker1 = new Thread(() -> {
            System.out.println("Worker 1 starting Stage 1 task.");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Worker 1 finished Stage 1 task.");
            stage1Latch.countDown(); // Worker 1 done with Stage 1
        });

        // Worker 2
        Thread worker2 = new Thread(() -> {
            System.out.println("Worker 2 starting Stage 1 task.");
            try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Worker 2 finished Stage 1 task.");
            stage1Latch.countDown(); // Worker 2 done with Stage 1
        });

        // Worker 3 (Stage 2)
        Thread worker3 = new Thread(() -> {
            try {
                stage1Latch.await(); // Wait for Worker 1 and Worker 2 to finish Stage 1
                System.out.println("Worker 3 starting Stage 2 task.");
                Thread.sleep(3000);
                System.out.println("Worker 3 finished Stage 2 task.");
                stage2Latch.countDown(); // Worker 3 done with Stage 2
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Worker 4 (Stage 3)
        Thread worker4 = new Thread(() -> {
            try {
                stage2Latch.await(); // Wait for Worker 3 to finish Stage 2
                System.out.println("Worker 4 starting Stage 3 task.");
                Thread.sleep(1000);
                System.out.println("Worker 4 finished Stage 3 task.");
                stage3Latch.countDown(); // Worker 4 done with Stage 3
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Start all workers
        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        // Main task waits for all workers to finish
        stage3Latch.await();
        System.out.println("All stages are completed. Main task can proceed.");
    }
}
