package com.paresh.practice.design.patterns.behavioral.command.pattern;

// Invoker
public class RemoteControl {
    Command slot;

    public RemoteControl() {
    }

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonPressed() {
        slot.execute();
    }

    public void undoButtonPressed() {
        slot.undo();
    }
}
