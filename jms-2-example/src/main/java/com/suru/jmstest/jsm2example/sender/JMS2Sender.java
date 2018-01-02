package com.suru.jmstest.jsm2example.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class JMS2Sender {
    public static void main(String[] args) {
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Queue queue = context.createQueue("JMS2_CRYPTO_TRADE.Q");
            context.createProducer()
                    .setProperty("sender", "SuRu")
                    .send(queue, "SELL 1000 XRP");
            System.out.println("message sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
