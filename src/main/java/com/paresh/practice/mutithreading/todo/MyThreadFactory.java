package com.paresh.practice.mutithreading.todo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

// ThreadFactory is used to create custom threads , we can set the name of the thread, priority of the thread etc.
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("PareshThread");
        t.setPriority(Thread.MAX_PRIORITY);
        return t;
    }

    public static void main (String ... args){
        MyThreadFactory factory = new MyThreadFactory();
        Thread t = factory.newThread(() -> System.out.println("Thread Name: "+Thread.currentThread().getName()));
        t.start();

        ExecutorService executorService = Executors.newFixedThreadPool(1, factory);
        executorService.execute(() -> System.out.println("Thread Name in Executor: "+Thread.currentThread().getName()));
        // execute vs submit
        // execute does not return anything, submit returns Future object
        executorService.shutdown();
    }
}
