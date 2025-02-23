package com.paresh.practice.mutithreading.executorservice;

public class DaemonThreadExample {

    public static void main(String ... args){
        MyRunnableTask myTask = new MyRunnableTask();
        Thread deamonThread = new Thread(myTask);
        deamonThread.setDaemon(true);
        deamonThread.start();
        // Main thread will not wait for the daemon thread to complete
        //Program will exit as soon as the main thread is done but the daemon thread will keep running
        System.out.println("Main thread is done");
        // If we do not set the thread as daemon, main thread will wait for the thread to complete
        // Does deamon thread keep running even after main thread is done?
        // Yes, deamon thread will keep running even after main thread is done
        // Deamon thread is used to perform background tasks
        // How we can observe the deamon thread running?
        // We can see the deamon thread running in the console
        // eXAMPLE: MyThread is running at : 1 Thread-0
        // But where are other iterations?
        // Deamon thread will keep running in the background
    }
}
