package com.paresh.practice.design.patterns.behavioral.template;

    /**
     * Abstract class representing a template for a connector.
     * This class defines the skeleton of an algorithm for connecting to a database,
     * executing a query, processing the result, and closing the connection.
     */
    public abstract class ConnectorTemplate {

        /**
         * Abstract method to be implemented by subclasses to set up the connection.
         */
        public abstract void setUp();

        /**
         * Connects to the database.
         * This method prints a message indicating that a connection is being established.
         */
        public void connect(){
            System.out.println("Connecting to Database");
        }

        /**
         * Executes a query.
         * This method prints a message indicating that a query is being executed.
         */
        public void execute(){
            System.out.println("Executing Query");
        }

        /**
         * Closes the connection.
         * This method prints a message indicating that the connection is being closed.
         */
        public void close(){
            System.out.println("Closing Connection");
        }

        /**
         * Template method that defines the sequence of steps for the connector.
         * This method calls the setup, connect, execute, processResult, postProcessHook, and close methods in order.
         */
        public void run(){
            setUp();
            connect();
            execute();
            processResult();
            postProcessHook();
            close();
        }

        /**
         * Hook method for post-processing.
         * This method can be overridden by subclasses to add additional behavior after processing the result.
         */
        public void postProcessHook() {
        }

        /**
         * Abstract method to be implemented by subclasses to process the result of the query.
         */
        public abstract void processResult();
    }