package com.restapi.restapidemo.kafka.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.restapidemo.entity.ProductPayload;
import com.restapi.restapidemo.service.serviceinterface.ProductPayloadService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class KafkaConsumer {
    @Autowired
    private ProductPayloadService service;
    @Autowired
    private ObjectMapper mapper;

    @KafkaListener(topics = "demo", groupId = "group-id")
    public void listen(ConsumerRecord<String,Object> message) {
        String receivedJson = message.value().toString();
        ProductPayload productPayload = null;
        try {
            productPayload = mapper.readValue(receivedJson,ProductPayload.class);
            productPayload.setCreatedDate((Timestamp) new Date());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        service.save(productPayload);
        System.out.println("Received Message in group - group-id: " + message.value().toString());
    }
}
