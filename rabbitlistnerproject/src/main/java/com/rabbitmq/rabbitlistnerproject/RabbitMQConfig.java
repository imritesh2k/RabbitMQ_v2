package com.rabbitmq.rabbitlistnerproject;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String MY_QUEUE = "MyQueue";

    /*
    Creating Queue with traditional approach
     */
    @Bean
    Queue myQueue(){
        return new Queue(MY_QUEUE,true); //for marking durable = true it will auto create the queue if not created under rabbitMQ
    }

    @Bean
    Exchange myTopicExchange(){
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

    /*
    Binding the exchange with queue and topic
     */
    @Bean
    Binding binding(){
        //Below is the traditional way of binding
        //return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE,"MyTopicExchange","topicRoutingKey",null);
        //Binding with binder
        return BindingBuilder
                .bind(myQueue())
                .to(myTopicExchange())
                .with("exchangeRoutingKey")
                .noargs();
    }

    /*
    Creating the connection
     */
    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListner());
        return simpleMessageListenerContainer;
    }


}
