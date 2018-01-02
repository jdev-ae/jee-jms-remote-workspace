package com.suru.jmstest.jms2features.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

public class JMS2SharedPublisher {
    public static void main(String[] args) {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Topic topic = context.createTopic("JMS2_CRYPTO_TRADE.T");
            context.createProducer().send(topic, "DOGE UPDATED 0.005695 USD");
            System.out.println("topic shared");
        }
    }
}
