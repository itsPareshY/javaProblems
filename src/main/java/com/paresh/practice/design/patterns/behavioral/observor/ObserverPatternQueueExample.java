package com.paresh.practice.design.patterns.behavioral.observor;

import java.util.*;

// Observer interface (Consumer)
interface MessageListener {
    void onMessageReceived(String message);
}

interface MessageQueue {
    void addMessageListener(MessageListener listener);  // Method to register an observer
    void removeMessageListener(MessageListener listener);  // Method to remove an observer
    void pushMessage(String message);  // Method to push a message to the queue and notify observers
    boolean hasMessages();  // Check if there are messages in the queue
    String getMessage();  // Retrieve and remove a message from the queue
}


// Subject class (Queue)
class ObservableQueue implements MessageQueue {
    private List<MessageListener> listeners = new ArrayList<>(); // List of observers
    private Queue<String> queue = new LinkedList<>(); // Internal queue to hold messages

    // Method to add an observer (consumer)
    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    // Method to remove an observer (consumer)
    public void removeMessageListener(MessageListener listener) {
        listeners.remove(listener);
    }

    // Method to push a message into the queue and notify observers
    public void pushMessage(String message) {
        queue.offer(message);  // Add message to the queue
        System.out.println("Producer pushed message: " + message);
        notifyListeners(message); // Notify all consumers (observers)
        // We can deliver to only one consumer to make it real producer -> MQ ->  consumer
        // once consumed get ACK from consumer then only remove from queue
    }

    // Notify all observers (consumers) about the new message
    private void notifyListeners(String message) {
        for (MessageListener listener : listeners) {
            listener.onMessageReceived(message);
        }
    }

    // Method to check if there are messages in the queue
    public boolean hasMessages() {
        return !queue.isEmpty();
    }

    // Method to get and remove a message from the queue
    public String getMessage() {
        return queue.poll();
    }
}

// Concrete consumer class (Observer)
class MessageConsumer implements MessageListener {
    private String name;

    public MessageConsumer(String name) {
        this.name = name;
    }

    @Override
    public void onMessageReceived(String message) {
        // Action: Print the message when received
        System.out.println(name + " consumed message: " + message);
    }
}

// Producer class
class MessageProducer {
    private ObservableQueue queue;

    public MessageProducer(ObservableQueue queue) {
        this.queue = queue;
    }

    // Push a message to the queue
    public void produceMessage(String message) {
        queue.pushMessage(message); // Push message to the queue
    }
}

public class ObserverPatternQueueExample {
    public static void main(String[] args) {
        // Create the observable queue (subject)
        ObservableQueue queue = new ObservableQueue();

        // Create consumers (observers)
        MessageConsumer consumer1 = new MessageConsumer("Consumer 1");
        MessageConsumer consumer2 = new MessageConsumer("Consumer 2");

        // Register consumers as listeners (observers)
        queue.addMessageListener(consumer1);
        queue.addMessageListener(consumer2);

        // Create producer
        MessageProducer producer = new MessageProducer(queue);

        // Producer pushes messages to the queue
        producer.produceMessage("Message 1");
        System.out.println("**********************************");
        queue.removeMessageListener(consumer1);
        producer.produceMessage("Message 2");
        System.out.println("Only consumer 2 will consume the message as we removed consumer 1");
        System.out.println("**********************************");
        queue.addMessageListener(consumer1);
        producer.produceMessage("Message 3");
    }
}
