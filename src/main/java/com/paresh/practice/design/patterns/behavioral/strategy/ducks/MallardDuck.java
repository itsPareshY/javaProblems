package com.paresh.practice.design.patterns.behavioral.strategy.ducks;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyWithWings;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        flyingBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a Mallard Duck");
    }
}
