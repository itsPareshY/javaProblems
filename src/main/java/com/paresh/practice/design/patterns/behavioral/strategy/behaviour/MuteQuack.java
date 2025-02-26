package com.paresh.practice.design.patterns.behavioral.strategy.behaviour;

public class MuteQuack implements QuackBehavior {

    public void quack(){
        System.out.println("I can't quack");
    }
}
