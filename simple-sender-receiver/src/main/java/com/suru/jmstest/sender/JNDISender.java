package com.suru.jmstest.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDISender {
    public static void main(String[] args) throws NamingException, JMSException {
        Context ctx = new InitialContext();
        // jndi.properties file located in /src/main/resources directory
        // ConnectionFactory lookup name mentioned in jndi.properties file
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // queue lookup name mentioned in jndi.properties file
        // queue configured in %ACTIVEMQ_HOME%/conf/activemq.xml file
        Queue queue = (Queue) ctx.lookup("EM_CRYPTO_TRADE.Q");

        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("BUY 100 XRP");
        producer.send(textMessage);
        System.out.println("message sent!");

        connection.close();
    }
}
