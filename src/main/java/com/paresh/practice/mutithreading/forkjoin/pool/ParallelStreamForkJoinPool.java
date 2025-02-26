package com.paresh.practice.mutithreading.forkjoin.pool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class demonstrates how to use a custom ForkJoinPool with a parallel stream.
 * #to set forkJoinpool threads in for parallel stream for JVM
 *
 *     java -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 MyClass
 *
 *
 *     although for better control use custom forkjoinpool with parallel streams
 */
public class ParallelStreamForkJoinPool {

    public static void main(String[] args) {
        // Create a custom ForkJoinPool with 4 threads
        ForkJoinPool customPool = new ForkJoinPool(4);

        List<Integer> numbers = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());

        // Submit the parallel stream to the custom pool

        customPool.submit(() ->
                numbers.parallelStream()
                        .map(number -> number * number)  // Square each number
                        .forEach(ParallelStreamForkJoinPool::printWithThreadName)    // Print the result
        ).join();  // Wait for the task to complete

        customPool.shutdown();  // Shutdown the pool after use

    }

    private static void printWithThreadName(Integer num) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " processing number: " + num);
    }
}
