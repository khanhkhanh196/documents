package com.restapi.restapidemo.kafka;

import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.exception.ProductException.ProductNotFoundException;
import com.restapi.restapidemo.kafka.sender.KafkaSender;
import com.restapi.restapidemo.service.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerApi {
    @Autowired
    private KafkaSender sender;
    @Autowired
    private ProductService productService;
    @GetMapping("/producer/{id}")
    public void sendMessage(@PathVariable int id) {
        Product product = productService.getProduct(id);
        if(product == null) {
            throw new ProductNotFoundException();
        } else {
            sender.sendObject(product);
        }

    }
}
