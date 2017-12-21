package com.suru.jmstest.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSReceiverMain {
    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://localhost:61616";
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        String queueName = "EM_CRYPTO_TRADE.Q";
        Queue queue = session.createQueue(queueName);

        MessageConsumer receiver = session.createConsumer(queue);

        // won't wait it will return null if there is no message in queue
        // receiver.receiveNoWait();

        // wait till timeout it will return null if there is no message in queue with in time
        // receiver.receive(5000);

        // wait until message arrives in queue
        TextMessage message = (TextMessage) receiver.receive();
        System.out.println("Message from JMS: " + message.getText());

        connection.close();

    }
}
