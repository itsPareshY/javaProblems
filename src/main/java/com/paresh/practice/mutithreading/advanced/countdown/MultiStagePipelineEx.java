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
        CountDownLatch latchStageTwo = new CountDownLatch(1);
        CountDownLatch latchStageThree = new CountDownLatch(1);


        Task3 task3 = new Task3(latchStageTwo);
        Task4 task4 = new Task4(latchStageThree);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executeStageOne(executorService);
        executeStageTwo(executorService, task3, latchStageTwo);
        executeStateThree(executorService, task4, latchStageThree);
        executorService.shutdown();
    }

    private static void executeStateThree(ExecutorService executorService, Task4 task4, CountDownLatch latchStageThree) throws InterruptedException {
        System.out.println("Stage 3 Started ...");
        executorService.execute(task4);
        latchStageThree.await();
        System.out.println("Stage 3 Completed ...");
    }

    private static void executeStageTwo(ExecutorService executorService, Task3 task3, CountDownLatch latchStageTwo) throws InterruptedException {
        System.out.println("Stage 2 Started ...");
        executorService.execute(task3);
        latchStageTwo.await();
        System.out.println("Stage 2 Completed ...");
    }

    private static void executeStageOne(ExecutorService executorService) throws InterruptedException {
        CountDownLatch latchStageOne = new CountDownLatch(2);
        Task1 task1 = new Task1(latchStageOne);
        Task2 task2 = new Task2(latchStageOne);
        System.out.println("Stage 1 Started ...");
        executorService.execute(task1);
        executorService.execute(task2);
        latchStageOne.await();
        System.out.println("Stage 1 Completed ...");
    }
}

/**
 * Task1 class representing a task in the first stage.
 */
class Task1 implements Runnable {
    CountDownLatch latch;

    /**
     * Constructor for Task1.
     *
     * @param latch CountDownLatch to signal task completion
     */
    public Task1(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * Run method to execute the task.
     */
    public void run() {
        System.out.println("Task 1 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 1 completed.");
        latch.countDown();
    }
}

/**
 * Task2 class representing a task in the first stage.
 */
class Task2 implements Runnable {
    CountDownLatch latch;

    /**
     * Constructor for Task2.
     *
     * @param latch CountDownLatch to signal task completion
     */
    public Task2(CountDownLatch latch) {
        this.latch = latch;
    }

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
        latch.countDown();
    }
}

/**
 * Task3 class representing a task in the second stage.
 */
class Task3 implements Runnable {
    CountDownLatch latch;

    /**
     * Constructor for Task3.
     *
     * @param latch CountDownLatch to signal task completion
     */
    public Task3(CountDownLatch latch) {
        this.latch = latch;
    }

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
        latch.countDown();
    }
}

/**
 * Task4 class representing a task in the third stage.
 */
class Task4 implements Runnable {
    CountDownLatch latch;

    /**
     * Constructor for Task4.
     *
     * @param latch CountDownLatch to signal task completion
     */
    public Task4(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * Run method to execute the task.
     */
    public void run() {
        System.out.println("Task 4 started ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 4 completed.");
        latch.countDown();
    }
}