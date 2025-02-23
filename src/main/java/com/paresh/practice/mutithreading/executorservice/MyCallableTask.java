package com.paresh.practice.mutithreading.executorservice;

import java.util.concurrent.Callable;

//Implementing Callable interface to create a task that returns a result
public class MyCallableTask implements Callable<String> {

    int instance ;

    public int getInstance() {
        return instance;
    }

    public MyCallableTask(int instance) {
        this.instance = instance;
    }

    @Override
    public String call() throws Exception {
        //Do some work
        for (int i = 1; i <= 500; i++) {
            System.out.println(" MyCallableTask " + instance + " is running at : " + i + " " + Thread.currentThread().getName());
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
