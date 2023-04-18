package com.sapo.publisher.service;

import com.sapo.publisher.model.OrderCreatedExchange;
import com.sapo.publisher.dao.OrderExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class OrderExchangeService {
    private final
    OrderExchangeDAO dao;

    public OrderExchangeService(OrderExchangeDAO dao) {
        this.dao = dao;
    }

    public OrderCreatedExchange getOrderExchangeById(int id) {
        OrderCreatedExchange order = dao.getOrderById(id);
        return order;
    }
}
