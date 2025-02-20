package com.paresh.practice.mutithreading.executorservice;

public class AnotherThread implements Runnable {

    @Override
    public void run() {
        //Do some work
        for (int i = 1; i <= 1000; i++) {
            System.out.println("AnotherThread is running at : " + i + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}