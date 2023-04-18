package com.sapo.publisher.dao;

import com.sapo.publisher.model.OrderCreatedExchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderExchangeDAO {
    private List<OrderCreatedExchange> orderCreatedExchangeList = new ArrayList<>();

    OrderCreatedExchange order1 = new OrderCreatedExchange(1,1000,"khanh","dung",true);
    OrderCreatedExchange order2 = new OrderCreatedExchange(2,1000,"binh","dung",false);
    OrderCreatedExchange order3 = new OrderCreatedExchange(3,1000,"nam","dung",false);
    OrderCreatedExchange order4 = new OrderCreatedExchange(4,1000,"van","dung",false);

    public List<OrderCreatedExchange> getOrderCreatedExchangeList() {
        orderCreatedExchangeList.add(order1);
        orderCreatedExchangeList.add(order2);
        orderCreatedExchangeList.add(order3);
        orderCreatedExchangeList.add(order4);
        return orderCreatedExchangeList;
    }

    public OrderCreatedExchange getOrderById(int i) {
        List<OrderCreatedExchange> list = getOrderCreatedExchangeList();
        for(OrderCreatedExchange ord : list) {
            if(ord.getOrderId() == i) {
                return ord;
            }
        }
        return null;
    }
}
