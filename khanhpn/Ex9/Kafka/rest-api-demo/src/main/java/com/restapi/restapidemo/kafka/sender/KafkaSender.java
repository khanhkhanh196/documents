package com.restapi.restapidemo.kafka.sender;

import com.restapi.restapidemo.dto.converter.ConvertProductDetailToPayload;
import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.entity.ProductPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ConvertProductDetailToPayload converter;
    public void sendObject(Product product) {
        ProductPayload payload = converter.convertProductToPayload(product);
        kafkaTemplate.send("demo", payload);

    }
}
