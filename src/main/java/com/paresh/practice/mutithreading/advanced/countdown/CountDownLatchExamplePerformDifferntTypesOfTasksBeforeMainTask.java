package com.paresh.practice.mutithreading.advanced.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * Explanation:
 * 3  tasks can be completed in parallel, but each task should complete before the main task can start.
 * The latch starts with a count of 3.
 * Each task calls countDown() after completing, which reduces the count by 1.
 * The main thread calls latch.await(), meaning it will wait until the count becomes 0 (after all tasks finish).
 * Once all tasks finish and the count reaches 0, the main thread can continue and execute its code.
 */
public class CountDownLatchExamplePerformDifferntTypesOfTasksBeforeMainTask {

    public static void main(String[] args) throws InterruptedException {
        // Create a latch with a count of 3
        CountDownLatch latch = new CountDownLatch(3);

        // Create three threads that do some work
        Thread task1 = new Thread(() -> {
            System.out.println("Task type 1 running...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task type 1 is done!");
            latch.countDown(); // Decrease the latch count
        });

        Thread task2 = new Thread(() -> {
            System.out.println("Task type 2 running...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task type 2 is done!");
            latch.countDown(); // Decrease the latch count
        });

        Thread task3 = new Thread(() -> {
            System.out.println("Task type 3 running...");
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task type 3 is done!");
            latch.countDown(); // Decrease the latch count
        });

        // Start the tasks
        task1.start();
        task2.start();
        task3.start();

        // Wait until the latch count reaches 0
        latch.await(); // Main thread will wait here

        // After all tasks are done, print a message
        System.out.println("All tasks are done. Now the main task can start.");
    }
}
