package com.paresh.practice.design.patterns.behavioral.strategy.ducks;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyNoWay;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.Squeak;

public class RubberDuck extends Duck {

    public RubberDuck(){
        quackBehavior = new Squeak();
        flyingBehavior = new FlyNoWay();
    }

    public void display(){
        System.out.println("I am a Rubber Duck");
    }
}
