package com.paresh.practice.design.patterns.behavioral.strategy.behaviour;

public class FlyNoWay implements FlyingBehavior {

    public void fly(){
        System.out.println("I can't fly");
    }
}
