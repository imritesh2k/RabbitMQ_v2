package com.rabbitmq.rabbitlistnerproject;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfiguration {

    /*
    Simple way of creating the exchange
     */
    @Bean
    Exchange exampleExchange(){
        return new TopicExchange("ExampleExchange");
    }

    /*
    Creating Exchange with the help of Exchange Builder
     */
    @Bean
    Exchange example2ndExchange(){
        return ExchangeBuilder.directExchange("Example2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    /*
    Creating Topic Exchange with the help of Exchange Builder
     */
    @Bean
    Exchange testTopicExchange(){
        return ExchangeBuilder.topicExchange("TestTopicExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    /*
   Creating FanOut Exchange with the help of Exchange Builder
    */
    @Bean
    Exchange testFanoutExchange(){
        return ExchangeBuilder.fanoutExchange("TestFanOutExchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    /*
   Creating Header Exchange with the help of Exchange Builder
    */
    @Bean
    Exchange headerExchange(){
        return ExchangeBuilder.headersExchange("HeaderTestExchange")
                .ignoreDeclarationExceptions()
                .durable(false)
                .internal()
                .build();
    }




}
