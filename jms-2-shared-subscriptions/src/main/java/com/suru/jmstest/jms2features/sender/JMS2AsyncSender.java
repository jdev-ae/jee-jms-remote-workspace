package com.suru.jmstest.jms2features.sender;

import javax.jms.CompletionListener;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import java.util.concurrent.CountDownLatch;

public class JMS2AsyncSender {
    public static void main(String[] args) {
        new JMS2AsyncSender();
    }

    public JMS2AsyncSender() {
        CountDownLatch latch = new CountDownLatch(1);
        ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = cf.createContext()) {
            Topic topic = context.createTopic("JMS2_CRYPTO_TRADE.T");
            context.createProducer()
                    .setAsync(new MyCompletionListener(latch))
                    .send(topic, "BUY 1200 XVG");
            for (int i = 0; i < 5; i++) {
                System.out.println("wait " + i);
                Thread.sleep(1000);
            }
            latch.await();
            System.out.println("done! everything");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MyCompletionListener implements CompletionListener {

        private final CountDownLatch latch;

        public MyCompletionListener(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onCompletion(Message message) {
            latch.countDown();
            System.out.println("message sending completed!");
        }

        @Override
        public void onException(Message message, Exception e) {
            latch.countDown();
            System.out.println("message sending failed! " + e);
        }
    }
}
