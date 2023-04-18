package com.restapi.restapidemo.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.restapidemo.entity.ProductPayload;
import com.restapi.restapidemo.service.serviceinterface.ProductPayloadService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Consumer implements MessageListener {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ProductPayloadService payloadService;
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "${rabbitmq.queue}")
    @Override
    public void onMessage(Message message) {
        System.out.println("Received <" + message.getBody().toString() + ">");
        byte[] body = message.getBody();
        String payload = new String(body);
        ProductPayload productPayload = null;
        try {
            productPayload = mapper.readValue(payload,ProductPayload.class);
            System.out.println(productPayload);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        payloadService.save(productPayload);
    }
}
