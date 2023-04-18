package com.sapo.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements MessageListener{

    private void ghtk() throws Exception {
        java.util.Random rand = new java.util.Random();
        int number = rand.nextInt(20) ;
        if (number % 20 == 0) {
            throw new Exception("GHTK error");
        }
    }
    private void sapoExpress() throws Exception {
        java.util.Random rand = new java.util.Random();
        int number = rand.nextInt(10);
        if (number % 10 == 0) {
            throw new Exception("SAPOExpress error");
        }
    }

    @RabbitListener(concurrency = "20",queues = "GHTK")
    public void listenToGHTKQueue(Object model) throws Exception {
        ghtk();
        System.out.println("listenToGHTKQueue  : " + model);
    }

    @RabbitListener(concurrency = "10",queues = "SAPOExpress")
    public void listenToSapoExpressQueue(Object model) throws Exception {
        sapoExpress();
        System.out.println("listenToSapoExpressQueue  :" + model);

    }

    @Override
    public void onMessage(Message message) {

    }
}
