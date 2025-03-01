package com.paresh.practice.mutithreading.synchronization;

public class InstanceMemberSynchronization {
    //Syncronized instance method
    public synchronized void instanceMethod() {
        System.out.println("Instance method is called by thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate a long running task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Instance method is completed by thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        InstanceMemberSynchronization obj1 = new InstanceMemberSynchronization();
        InstanceMemberSynchronization obj2 = new InstanceMemberSynchronization();

        Runnable task1 = obj1::instanceMethod;
        Runnable task2 = obj2::instanceMethod;

        // Create 10 threads for calling the synchronized instance method on obj1
        for(int i = 0; i < 10; i++) {
            new Thread((task1),"Object-1-Thread").start();
        }

        // Create 10 threads for calling the synchronized instance method on obj2
        for (int i = 0; i < 10; i++) {
            new Thread((task2), "Object-2-Thread").start();
        }

        //we can see that the synchronized instance method is called by only one thread at a time for each object.
        // But the synchronized instance method is called by multiple threads at a time for different objects.
    }
}
