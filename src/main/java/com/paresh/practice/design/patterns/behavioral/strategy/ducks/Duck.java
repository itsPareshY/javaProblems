package com.paresh.practice.design.patterns.behavioral.strategy.ducks;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyingBehavior;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.QuackBehavior;

public abstract class Duck {

    FlyingBehavior flyingBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }

    // Custom behaviour of Duck
    public void performFly(){
        flyingBehavior.fly();
    }
    //Custom behaviour of Duck
    public void performQuack(){
        quackBehavior.quack();
    }

    // Specific to each Duck type
    public abstract void display();

    //Default behaviour of Duck - applies to all ducks
    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }

    public void setFlyingBehavior(FlyingBehavior flyingBehavior) {
        this.flyingBehavior = flyingBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
