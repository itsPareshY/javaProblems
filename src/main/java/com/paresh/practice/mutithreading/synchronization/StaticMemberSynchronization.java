package com.paresh.practice.mutithreading.synchronization;

public class StaticMemberSynchronization {
    public static synchronized void staticMethod() {
        System.out.println(Thread.currentThread().getName() + " is inside static method.");
        try {
            Thread.sleep(2000); // Simulate a long running task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        public static void main(String args[]) {
            Runnable task1 = StaticMemberSynchronization::staticMethod;

            // Create 10 threads for calling the synchronized static method
            for (int i = 0; i < 10; i++) {
                new Thread(task1, "Thread-"+i).start();
            }

            //we can see that the synchronized static method is called by only one thread at a time.
            //All threads although started  are waiting for the previous thread to complete the static method.
        }
    }
