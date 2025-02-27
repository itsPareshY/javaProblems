package com.paresh.practice.design.patterns.behavioral.command.pattern;

// Client
public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        remoteControl.setCommand(lightOn);
        remoteControl.buttonPressed();
        remoteControl.undoButtonPressed();
    }
}
