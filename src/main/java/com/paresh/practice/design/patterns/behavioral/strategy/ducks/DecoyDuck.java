package com.paresh.practice.design.patterns.behavioral.strategy.ducks;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyWithRocket;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.MuteQuack;

public class DecoyDuck extends Duck {

    public DecoyDuck() {
        this.flyingBehavior = new FlyWithRocket();
        this.quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("I am a Decoy Duck");
    }
}
