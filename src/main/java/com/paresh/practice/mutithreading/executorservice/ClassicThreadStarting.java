package com.paresh.practice.mutithreading.executorservice;

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
        MyCallable myCallable = new MyCallable(1);
        String resultFromCallable = myCallable.call();
        System.out.println(resultFromCallable);

        // Above we created a thread using Thread class, Runnable interface and Callable interface
        // All of them are running in parallel
        // Threads start running in parallel as soon as we call start() method
        // We can use join() method to wait for a thread to complete before moving forward
        // join() method is used to wait for a thread to complete before moving forward
        // Callables can be used to get the result of a task
        // We start Callable by calling call() method and it starts running in parallel


    }
}
