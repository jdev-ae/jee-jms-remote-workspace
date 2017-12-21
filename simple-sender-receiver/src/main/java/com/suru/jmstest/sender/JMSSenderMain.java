package com.suru.jmstest.sender;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSSenderMain {
    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://localhost:61616";
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        String queueName = "EM_CRYPTO_TRADE.Q";
        Queue queue = session.createQueue(queueName);

        MessageProducer sender = session.createProducer(queue);

        TextMessage textMessage = session.createTextMessage("BUY 100 BTC");
        sender.send(textMessage);

        System.out.println("message sent!");

        connection.close();
    }
}
