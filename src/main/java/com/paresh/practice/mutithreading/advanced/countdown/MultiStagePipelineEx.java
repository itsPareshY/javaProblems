package com.paresh.practice.mutithreading.advanced.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example of a multi-stage pipeline using CountDownLatch.
 * This class demonstrates how to coordinate multiple tasks across different stages.
 */
public class MultiStagePipelineEx {
        
    /**
     * Main method to execute the multi-stage pipeline.
     *
     * @param args Command line arguments
     * @throws InterruptedException if any thread is interrupted while waiting
     */
    public static void main(String... args) throws InterruptedException {

        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        Task4 task4 = new Task4();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executeStage(executorService, "1", task1 , task2);
        executeStage(executorService, "2", task3);
        executeStage(executorService, "3", task4);
        executorService.shutdown();
    }

    private static void executeStage(ExecutorService executorService, String stage, Runnable ... tasks) throws InterruptedException {
        CountDownLatch stageLatch = new CountDownLatch(tasks.length);
        System.out.printf("Starting Stage %s ...%n", stage);
        for(Runnable task : tasks) {
            executorService.execute(() -> {
                task.run();
                stageLatch.countDown();
            });
        }
        stageLatch.await();
        System.out.printf("Stage %s complete.%n", stage);
    }
}

/**
 * Task1 class representing a task in the first stage.
 */
class Task1 implements Runnable {
    public void run() {
        System.out.println("Task 1 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 1 completed.");
    }
}

/**
 * Task2 class representing a task in the first stage.
 */
class Task2 implements Runnable {
    /**
     * Run method to execute the task.
     */
    public void run() {
        System.out.println("Task 2 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 2 completed.");
    }
}

/**
 * Task3 class representing a task in the second stage.
 */
class Task3 implements Runnable {
    /**
     * Run method to execute the task.
     */
    public void run() {
        System.out.println("Task 3 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 3 completed.");
    }
}

/**
 * Task4 class representing a task in the third stage.
 */
class Task4 implements Runnable {
    public void run() {
        System.out.println("Task 4 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 4 completed.");
    }
}