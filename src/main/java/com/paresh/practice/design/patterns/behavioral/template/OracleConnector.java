package com.paresh.practice.design.patterns.behavioral.template;

public class OracleConnector extends ConnectorTemplate {

    @Override
    public void setUp() {
        System.out.println("Setting up Oracle Connection");
    }


    @Override
    public void processResult() {
        System.out.println("Processing Oracle Result");
    }
}
