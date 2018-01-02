package com.suru.jmstest.jms2features.receiver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

public class JMS2SharedSubscriber1 implements MessageListener {
    public static void main(String[] args) {
        new JMS2SharedSubscriber1();
    }

    public JMS2SharedSubscriber1() {
        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            JMSContext context = cf.createContext();
            Topic topic = context.createTopic("JMS2_CRYPTO_TRADE.T");
            JMSConsumer consumer = context.createSharedConsumer(topic, "SUB: DOGE");
            consumer.setMessageListener(this);
            System.out.println("waiting " + getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(getClass().getSimpleName() + ", message: " + message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
