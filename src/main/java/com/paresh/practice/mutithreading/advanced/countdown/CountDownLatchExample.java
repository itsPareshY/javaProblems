package com.paresh.practice.mutithreading.advanced.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        for(int i = 0; i < 3; i++) {
            Thread worker = new Thread(() -> {
                System.out.println("Worker is working...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Worker has completed its task.");
                latch.countDown();
            });
            worker.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All workers have completed their tasks.");
    }
}
