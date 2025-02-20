package com.paresh.practice.mutithreading.executorservice;

import java.util.concurrent.Callable;

//Implementing Callable interface to create a task that returns a result
public class MyCallable implements Callable<String> {

    int instance ;

    public int getInstance() {
        return instance;
    }

    public MyCallable(int instance) {
        this.instance = instance;
    }

    @Override
    public String call() throws Exception {
        //Do some work
        for (int i = 1; i <= 500; i++) {
            System.out.println(" MyCallable " + instance + " is running at : " + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "MyCallable " +instance +" done";
    }
}
