package com.paresh.practice.design.patterns.behavioral.template;

public class Client {
    public static void main(String[] args) {
        ConnectorTemplate connector = new OracleConnector();
        connector.run();
        System.out.println("=====================================");
        connector = new MySqlConnector();
        connector.run();
    }
}
