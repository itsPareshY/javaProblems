package com.paresh.practice.design.patterns.behavioral.strategy;

import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.FlyNoWay;
import com.paresh.practice.design.patterns.behavioral.strategy.behaviour.Squeak;
import com.paresh.practice.design.patterns.behavioral.strategy.ducks.*;

public class Client {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();
        mallard.swim();


        mallard.setFlyingBehavior(new FlyNoWay());
        mallard.performFly();
        mallard.setQuackBehavior(new Squeak());
        mallard.performQuack();

        Duck redhead = new RedheadDuck();
        redhead.display();
        redhead.performFly();
        redhead.performQuack();
        redhead.swim();

        Duck rubber = new RubberDuck();
        rubber.display();
        rubber.performFly();
        rubber.performQuack();
        rubber.swim();

        Duck decoy = new DecoyDuck();
        decoy.display();
        decoy.performFly();
        decoy.performQuack();
        decoy.swim();
        decoy.setQuackBehavior(new Squeak());
        decoy.performQuack();
    }
}
