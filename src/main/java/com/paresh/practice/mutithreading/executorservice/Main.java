package com.paresh.practice.mutithreading.executorservice;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ArrayList<Future<String>> results = new ArrayList<>();
        //Submit n tasks to the executor service with n threads in pool
        ExecutorService service = Executors.newFixedThreadPool(10);

        //TODO Executors.newScheduledThreadPool() ->  Scheduled thread pool is used for scheduling tasks
        //TODO Executors.newWorkStealingPool() -> WorkStealingPool is used for parallel processing
        //TODO Executors.newSingleThreadExecutor() -> is used for single thread processing

        // 1 submit uses 1 thread
        // atmost 2 threads will be used as we submit only 2 tasks even if we have 10 threads in pool
        service.submit(new MyThread());
        //Runnable task MyThread and AnotherThread only perform task and do not return any result
        //Thats why they implement Runnable with run method
        service.submit(new AnotherThread());
        for (int i = 1; i <= 10; i++) {
            //MyCallable to submit task and get result
            Future<String> future = service.submit(new MyCallable(i));
            results.add(future);
        }
        // as we submitted MyThread and AnotherThread first (2 threads) and
        // then 8 Threads used by first 8 MyCallable instances
        // 2+8 = 10 threads used so 9th and 10th MyCallable instances will wait for any of the first 8 threads to complete
        for (Future<String> future : results) {
            try {
                // future.get() ensures that the task is completed before we move forward
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}