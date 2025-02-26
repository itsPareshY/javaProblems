package com.paresh.practice.mutithreading.classic;

import com.paresh.practice.mutithreading.executorservice.MyCallableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClassicThreadStarting {

    public static void main(String[] args) throws Exception {
        // Create a thread using Thread class
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join(); // this will cause the program to wait for myThread to complete before moving forward

        // Create a thread using Runnable interface
        MyRunnableTask myRunnableTask = new MyRunnableTask();
        //Supplying Runnable to Thread class
        Thread thread = new Thread(myRunnableTask);
        // thread.run(); // this will not start a new thread, it will run in the same thread
        // DO not call run() method, call start() method to start a new thread
        thread.start();

        // Create a thread using Callable interface
        // Callable Tasks cannot be started using Thread class
        // We need to use ExecutorService to start a Callable Task
        // If we invoke call() method directly, it will run in the same thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyCallableTask myCallableTask = new MyCallableTask(1);
        Future<String> result = executorService.submit(myCallableTask);
        String resultFromCallable = result.get();
        executorService.shutdown();
        // WE use Future to get the result of a task
        // Future.get() method waits for the task to complete before moving forward
        System.out.println(resultFromCallable);
        // Main thread complete but myrunnableTask and myCallableTask keep running
        // the program will not exit until all the threads are done

        System.out.println("Main thread is done");
        // Above we created a thread using Thread class, Runnable interface and Callable interface
        // All of them are running in parallel
        // Threads start running in parallel as soon as we call start() method
        // We can use join() method to wait for a thread to complete before moving forward
        // join() method is used to wait for a thread to complete before moving forward
        // Callables can be used to get the result of a task
        // We start Callable by calling call() method and it starts running in parallel


    }
}
