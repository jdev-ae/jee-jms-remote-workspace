package com.suru.jmstest.jms2features.receiver;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

public class JMS2AsyncQueueReceiver implements MessageListener {
    public static void main(String[] args) {
        new JMS2AsyncQueueReceiver();
    }

    private JMSContext context;

    public JMS2AsyncQueueReceiver() {
        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            context = cf.createContext(Session.SESSION_TRANSACTED);
            Queue queue = context.createQueue("TEST_JMS2.Q");
            context.createConsumer(queue)
                    .setMessageListener(this);
            System.out.println("waiting!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Received: " + message.getBody(String.class));
            Thread.sleep(500);
            // JMSXDeliveryCount is a mandatory property in JMS 2.0
            // we can use to detect error messages
            if (message.getIntProperty("JMSXDeliveryCount") > 2) {
                context.commit();
                System.out.println("done with everything!");
                return;
            }
            context.rollback();
            System.out.println("retry count: " + message.getIntProperty("JMSXDeliveryCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
* output
    waiting!
    Received: SELL 100 BTC
    retry count: 1
    Received: SELL 100 BTC
    retry count: 2
    Received: SELL 100 BTC
    done with everything!
    retry count: 3
* */