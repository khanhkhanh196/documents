package com.restapi.restapidemo.rabbitmq;

import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.entity.ProductPayload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    public String exchange;
    @Value("${rabbitmq.routing}")
    public String routingKey;
    @Value("${rabbitmq.queue}")
    public  String queue;

    public void send(ProductPayload product) {
        rabbitTemplate.convertAndSend(queue, product);
        System.out.println("Send msg = " + product);

    }
}
