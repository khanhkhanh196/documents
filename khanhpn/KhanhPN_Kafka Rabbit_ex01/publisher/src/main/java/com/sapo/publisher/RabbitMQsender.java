package com.sapo.publisher;

import com.rabbitmq.client.Channel;
import com.sapo.publisher.model.OrderCreatedExchange;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RabbitMQsender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    public  String queueGHTK;
    @Value("${rabbitmq.queueSAPO}")
    public  String queueSAPOEx;



    public RabbitMQsender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrderExchangeToGHTK(OrderCreatedExchange payload) {
        rabbitTemplate.convertAndSend(queueGHTK, payload,postProcessor(payload));
    }

    public void sendOrderExchangeToSAPOExpress(OrderCreatedExchange payload) {
        rabbitTemplate.convertAndSend(queueSAPOEx, payload,postProcessor(payload));

    }

    public MessagePostProcessor postProcessor(OrderCreatedExchange payload) {
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                if(payload.isVip() == true) {
                    message.getMessageProperties().setPriority(5);
                } else {
                    message.getMessageProperties().setPriority(1);
                }
                return message;
            }
        };
        return processor;
    }
}
