package com.paresh.practice.design.patterns.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleTonENUM {

    /**
     * Singleton ENUM
     * - Enum is thread safe
     * - Enum is serializable
     * - Enum is reflection safe
     * - Enum is lazy loaded
     * - Enum is singleton
     * - Enum is best way to create singleton
     * - Enum is best way to create shared resources
     * This method demonstrates the use of Singleton ENUM by creating a shared resource (ExecutorService). 
     * The ExecutorService is created only once and shared across multiple instances of the Singleton ENUM.
     * @param args
     */
    public static void main(String ... args){
        Singleton instance1 = Singleton.INSTANCE;
        //instance 1 and instance 2 will have same executor service
        System.out.println(instance1.getExecutorService());
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance2.getExecutorService());

        instance1.putInCache(1, "One");
        instance2.putInCache(2, "Two");
        // i put data in cache instance1 and instance2 but as they are same instance,
        // they will have same cache shown by printCache method
        instance1.printCache();
        System.out.println("Instance 1 Cache: " + instance1.getCache());
        System.out.println("Instance 2 Cache: " + instance2.getCache());
    }

    /**
     * create shared resources
     * create connection pool
     * create thread pool
     * create cache
     * create configuration
     * create logger
     * create registry
     * create scheduler
     * create executor
     * create factory
     * stateless beans (Controller, Service, DAO)
     */
    public enum Singleton {
        INSTANCE;

        public void doSomething(){
            System.out.println("Singleton ENUM");
        }

        private ExecutorService executorService = Executors.newFixedThreadPool(10);

        public ExecutorService getExecutorService(){
            return executorService;
        }

        private Map<Integer, String> myCache = new HashMap<>();

        public void putInCache(Integer key, String value){
            myCache.put(key, value);
        }

        public String getFromCache(Integer key){
            return myCache.get(key);
        }

        public void removeFromCache(Integer key){
            myCache.remove(key);
        }

        public void clearCache(){
            myCache.clear();
        }

        public  Map getCache(){
            return myCache;
        }

        public void printCache() {
            myCache.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        }
    }
}
