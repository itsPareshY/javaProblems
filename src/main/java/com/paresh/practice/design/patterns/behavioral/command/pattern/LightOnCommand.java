package com.paresh.practice.design.patterns.behavioral.command.pattern;

// Concrete Command
public class LightOnCommand implements Command {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.switchOn();
    }

    @Override
    public void undo() {
        light.switchOff();
    }
}
