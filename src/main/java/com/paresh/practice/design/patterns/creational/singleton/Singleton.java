package com.paresh.practice.design.patterns.creational.singleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;

    //Static member holds only one instance of the Singleton class
    private static Singleton instance = null;

    //Eager Initialization Singleton
    //private static final Singleton instanceEager = new Singleton();

    //Use Eager Initialization Singleton if your singleton class is not using much resources
    //Use Lazy Initialization Singleton if your singleton class is using much resources
    //Use Double Checked Locking Singleton if your singleton class is using much resources and you want to avoid synchronization overhead


    private Singleton() {
        //Prevent form the reflection api.
        // Throw a runtime exception if someone tries to create an instance of Singleton using the reflection api
        if (instance != null) {
            throw new IllegalStateException("Alreadycreated.");
        }
    }

    //Double Checked Locking Singleton
    //Multiple threads can access this method at the same time
    //Thread safety is guaranteed
    public static Singleton getInstanceDoubleCheckedLocking() {
        //Lazy initialization of Singleton
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Prevent cloning of Singleton -> throw exception from clone method
    // prevent reflection -> throw exception from constructor if instance is not null
    // prevent serialization -> implement writeReplace method and return the same instance
    // prevent deserialization -> implement readResolve method and return the same instance
    // prevent multiple class loaders ->
    // prevent multiple threads -> use synchronized block in getInstance method

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this class is not allowed");
    }

    //Read the object from the stream
    protected Object readResolve() {
        return instance;
    }

    //Write the object to the stream
    protected Object writeReplace() {
        return instance;
    }

    //implement getClass method to prevent multiple class loaders
    public static Class getClass(String className) throws ClassNotFoundException {
        return Class.forName(className);

    }

    public static void main(String ... args){
        Singleton singleton = Singleton.getInstanceDoubleCheckedLocking();
        System.out.println(singleton);
        Singleton singleton1 = Singleton.getInstanceDoubleCheckedLocking();
        System.out.println(singleton1);
    }

}

