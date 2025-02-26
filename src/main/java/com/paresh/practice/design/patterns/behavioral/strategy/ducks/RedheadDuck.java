package com.paresh.practice.design.patterns.behavioral.strategy.ducks;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyNoWay;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyWithWings;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.Quack;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.Squeak;

public class RedheadDuck extends Duck {

    public RedheadDuck() {
        this.flyingBehavior = new FlyWithWings();
        this.quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a Redhead Duck");
    }
}
