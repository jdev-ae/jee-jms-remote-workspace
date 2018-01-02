package com.suru.jmstest.jms2features.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class JMS2SimpleQueueSender {
    public static void main(String[] args) {
        new JMS2SimpleQueueSender();
    }

    public JMS2SimpleQueueSender() {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Queue queue = context.createQueue("TEST_JMS2.Q");
            context.createProducer().send(queue, "SELL 100 BTC");
            System.out.println("sending done!");
        }
    }
}
