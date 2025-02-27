package com.paresh.practice.design.patterns.behavioral.command.pattern;

// Receiver
public class Light {
    public void switchOn() {
        System.out.println("Light is on");
    }

    public void switchOff() {
        System.out.println("Light is off");
    }
}
