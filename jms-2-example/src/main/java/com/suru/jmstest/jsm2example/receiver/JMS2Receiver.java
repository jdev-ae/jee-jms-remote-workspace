package com.suru.jmstest.jsm2example.receiver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

public class JMS2Receiver {
    public static void main(String[] args) {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Queue queue = context.createQueue("JMS2_CRYPTO_TRADE.Q");
//            String receive = context.createConsumer(queue).receiveBody(String.class);
            Message message = context.createConsumer(queue).receive();
            System.out.println("Message: " + message.getBody(String.class));
            System.out.println("StringProperty('sender'): " + message.getStringProperty("sender"));
            System.out.println("done receive!");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
