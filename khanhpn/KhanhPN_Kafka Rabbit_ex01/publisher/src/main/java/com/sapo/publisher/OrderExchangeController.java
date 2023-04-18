package com.sapo.publisher;


import com.sapo.publisher.model.OrderCreatedExchange;
import com.sapo.publisher.service.LoggingService;
import com.sapo.publisher.service.OrderExchangeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class OrderExchangeController {
    private final RabbitMQsender rabbitMQSender;
    private final OrderExchangeService service;

    private final LoggingService logging;

    public OrderExchangeController(RabbitMQsender rabbitMQSender, OrderExchangeService service, LoggingService logging) {
        this.rabbitMQSender = rabbitMQSender;
        this.service = service;
        this.logging = logging;
        logging.initialLogger();
    }

    @RequestMapping(value = "admin/order/ghtk/{id}",method = RequestMethod.GET)
    public String sendMStoGTHK(@PathVariable int id) {
        OrderCreatedExchange orderExchangeById = service.getOrderExchangeById(id);
        rabbitMQSender.sendOrderExchangeToGHTK(orderExchangeById);
        logging.printOrderToFile(orderExchangeById);
        return "MQ succesfully sent to GHTK queue";
    }

    @RequestMapping(value = "admin/order/sapoex/{id}",method = RequestMethod.GET)
    public String sendMStoSAPOExpress(@PathVariable int id){
        OrderCreatedExchange orderExchangeById = service.getOrderExchangeById(id);
        rabbitMQSender.sendOrderExchangeToSAPOExpress(orderExchangeById);
        logging.printOrderToFile(orderExchangeById);
        return "MQ succesfully sent to SAPOExpress queue";
    }
}
