package com.paresh.practice.design.patterns.behavioral.template;

public class MySqlConnector extends ConnectorTemplate {

    @Override
    public void setUp() {
        System.out.println("Setting up MySql Connection");
    }

    @Override
    public void processResult() {
        System.out.println("Processing MySql Result");
    }

    @Override
    public void postProcessHook() {
        super.postProcessHook();
        System.out.println("Demo Hook - Overriden only for MySql. Post Processing MySql Result");
    }
}
