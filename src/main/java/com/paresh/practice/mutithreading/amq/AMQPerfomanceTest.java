package com.paresh.practice.mutithreading.amq;

//import org.apache.camel.CamelContext;
//import org.apache.camel.impl.DefaultCamelContext;

public class AMQPerfomanceTest {

    private static final String QUEUE_NAME = "testQueue";
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final int MESSAGE_COUNT = 1000000;
    private static final int BATCH_SIZE = 200;
    private static final int CONSUMER_COUNT = 10;
    private static final int PRODUCER_COUNT = 10;
    private static final int MESSAGE_SIZE = 2048;

    public static void main(String[] args) {
//       CamelContext context = new DefaultCamelContext();

       //context.addComponent("activemq", ActiveMQComponent.activeMQComponent(BROKER_URL));

    }
}
