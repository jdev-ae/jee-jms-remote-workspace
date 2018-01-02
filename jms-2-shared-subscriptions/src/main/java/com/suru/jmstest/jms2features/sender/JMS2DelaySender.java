package com.suru.jmstest.jms2features.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

public class JMS2DelaySender {
    public static void main(String[] args) {
        new JMS2AsyncSender();
    }

    public JMS2DelaySender() {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Topic topic = context.createTopic("JMS2_CRYPTO_TRADE.T");
            context.createProducer()
                    .setDeliveryDelay(5000)
                    .send(topic, "SEND DELAY MSG");
            System.out.println("message will processed after 5 sec!");
        }
    }
}
