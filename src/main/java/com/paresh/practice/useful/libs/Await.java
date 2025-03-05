package com.paresh.practice.useful.libs;

import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Await {

    // This is a simple example of how to use Awaitility library to wait for 2 seconds.
    // can be used in test classes to wait for a certain condition to be true.
    public static void main(String[] args) {
        System.out.println("Waiting for 2 seconds...");
        await().pollDelay(2, SECONDS).until(() -> true);
        System.out.println("Done waiting for 2 seconds.");
    }
}
