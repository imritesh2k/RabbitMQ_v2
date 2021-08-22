package com.rabbitmq.rabbitmqproject1;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
    public static final String MESSAGE_QUEUE = "ritesh-message-queue";
    public static final String MESSAGE_QUEUE_DEAD_LETTER = "ritesh-message-queue-dlq";
    public static final String MESSAGE_EXCHANGE = "ritesh-message-exchange";
    public static final String MESSAGE_EXCHANGE_DEAD_LETTER = "ritesh-message-exchange-dlx";
    public static final String MESSAGE_ROUTING_KEY = "ritesh-message-routing-key";
    public static final String MESSAGE_ROUTING_KEY_DEAD_LETTER = "ritesh-message-routing-key-dl";

    @Bean
    DirectExchange messageExchnage(){
        return new DirectExchange(MESSAGE_EXCHANGE);
    }

    @Bean
    DirectExchange deadLetterMessageExchnage(){
        return new DirectExchange(MESSAGE_EXCHANGE_DEAD_LETTER);
    }

    @Bean
    Queue messageQueue(){
        return QueueBuilder.durable(MESSAGE_QUEUE)
                .withArgument("x-dead-letter-exchange", MESSAGE_EXCHANGE_DEAD_LETTER)
                .withArgument("x-dead-letter-routing-key",MESSAGE_ROUTING_KEY_DEAD_LETTER)
                .build();
    }

    @Bean
    Queue messageDLQueue(){
        return QueueBuilder.durable(MESSAGE_QUEUE_DEAD_LETTER).build();
    }

    @Bean
    Binding bindingMessage(){
        return BindingBuilder.bind(messageQueue()).to(messageExchnage()).with(MESSAGE_ROUTING_KEY);
    }

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
        simpleMessageListenerContainer.setQueues(messageQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMessageListener());
        return simpleMessageListenerContainer;
    }
}
