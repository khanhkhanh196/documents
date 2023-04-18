package com.restapi.restapidemo.rabbitmq;

import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.payload.product.ConvertProductDetailToPayload;
import com.restapi.restapidemo.entity.ProductPayload;
import com.restapi.restapidemo.service.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitMQController {
    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private ProductService productService;
    @Autowired
    private ConvertProductDetailToPayload converter;
    @GetMapping(value = "/producer/{id}",produces = "application/json")
    public String producer(@PathVariable int id) {
        Product productDetail = productService.getProduct(id);
        ProductPayload payload = converter.convertProductToPayload(productDetail);
        rabbitMQSender.send(payload);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
