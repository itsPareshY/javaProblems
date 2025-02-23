package com.paresh.practice.mutithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    /**
     * Future is used to get the result of the task submitted to the executor service
     * Task is created using Callable interface
     * @param args
     */
    public static void main(String ... args){
        MyCallableTask myCallableTask = new MyCallableTask(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(myCallableTask);
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
