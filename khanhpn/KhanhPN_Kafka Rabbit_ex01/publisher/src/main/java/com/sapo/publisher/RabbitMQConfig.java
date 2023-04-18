package com.sapo.publisher;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange}")
    public  String directExchangeName;

    @Value("${rabbitmq.queue}")
    public  String queueGHTK;
    @Value("${rabbitmq.queueSAPO}")
    public  String queueSAPOEx;

    @Value("${rabbitmq.routing}")
    public  String routingKey;
    @Value("${rabbitmq.routingSAPO}")
    public  String routingKeySAPO;

    @Value("${rabbitmq.host}")
    public String rabbitHost;
    @Value("${rabbitmq.port}")
    public int port;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-priority", 10);
        args.put("x-dead-letter-exchange", "deadLetterExchange");
        args.put("x-dead-letter-routing-key", "deadLetter");
        return new Queue(queueGHTK, true,false,false,args);
    }
    @Bean
    Queue queueSAPO() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-max-priority", 10);
        args.put("x-dead-letter-exchange", "deadLetterExchange");
        args.put("x-dead-letter-routing-key", "deadLetter");
        return new Queue(queueSAPOEx, true,false,false,args);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName,true,false);
    }

    @Bean
    @Autowired
    Binding binding(@Qualifier("queue")Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    @Bean
    @Autowired
    Binding bindingSAPO(@Qualifier("queueSAPO") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeySAPO);
    }

    //Dead letter Exchange Delcare
    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("deadLetterExchange");
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable("deadLetter.queue").build();
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("deadLetter");
    }

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory() ;

        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        connectionFactory.setHost(rabbitHost);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
