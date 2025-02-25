package com.paresh.practice.design.patterns.behavioral.observor.chainof.responibility.example.with.strategy;

// Define the Strategy interface for canProcess decision
interface CanProcessStrategy {
    boolean canProcess(String request);
}

// Concrete strategy for HandlerA
class HandlerAStrategy implements CanProcessStrategy {
    @Override
    public boolean canProcess(String request) {
        return request.equals("Process A");
    }
}

// Concrete strategy for HandlerB
class HandlerBStrategy implements CanProcessStrategy {
    @Override
    public boolean canProcess(String request) {
        return request.equals("Process B");
    }
}

// Concrete strategy for HandlerC
class HandlerCStrategy implements CanProcessStrategy {
    @Override
    public boolean canProcess(String request) {
        return request.equals("Process C");
    }
}

// Define the abstract handler class
abstract class RequestHandler {
    protected RequestHandler nextHandler;
    protected CanProcessStrategy strategy;

    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Use strategy to decide if the request can be processed
    public void handleRequest(String request) {
        if (strategy.canProcess(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);  // Pass request to the next handler
        } else {
            System.out.println(this.getClass().getSimpleName() + " cannot process request: " + request);
        }
    }

    protected abstract void processRequest(String request);
}

// Concrete Handler 1 - Will process 'Process A' requests
class HandlerA extends RequestHandler {
    public HandlerA(CanProcessStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected void processRequest(String request) {
        System.out.println("HandlerA is processing request: " + request);
    }
}

// Concrete Handler 2 - Will process 'Process B' requests
class HandlerB extends RequestHandler {
    public HandlerB(CanProcessStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected void processRequest(String request) {
        System.out.println("HandlerB is processing request: " + request);
    }
}

// Concrete Handler 3 - Will process 'Process C' requests
class HandlerC extends RequestHandler {
    public HandlerC(CanProcessStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected void processRequest(String request) {
        System.out.println("HandlerC is processing request: " + request);
    }
}

// Main class to run the Chain of Responsibility pattern
public class ChainOfResponsibilityExample {

    public static void main(String[] args) {
        // Create handler objects with associated strategies
        HandlerA handlerA = new HandlerA(new HandlerAStrategy());
        HandlerB handlerB = new HandlerB(new HandlerBStrategy());
        HandlerC handlerC = new HandlerC(new HandlerCStrategy());

        // Set up the chain (i.e., the object tree or branch)
        handlerA.setNextHandler(handlerB);  // handlerA -> handlerB
        handlerB.setNextHandler(handlerC);  // handlerB -> handlerC

        // Process various requests
        System.out.println("Request: Process A");
        handlerA.handleRequest("Process A");  // Expected to be processed by HandlerA

        System.out.println("\nRequest: Process B");
        handlerA.handleRequest("Process B");  // Expected to be processed by HandlerB

        System.out.println("\nRequest: Process C");
        handlerA.handleRequest("Process C");  // Expected to be processed by HandlerC

        System.out.println("\nRequest: Process D");
        handlerA.handleRequest("Process D");  // No handler should process it
    }
}
