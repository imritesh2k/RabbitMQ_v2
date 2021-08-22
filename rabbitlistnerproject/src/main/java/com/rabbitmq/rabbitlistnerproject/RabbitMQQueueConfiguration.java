package com.rabbitmq.rabbitlistnerproject;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {

    /*
    Creating Queue the way 1
     */
    @Bean
    Queue exampleQueue(){
        return new Queue("ExampleQueue", false);
    }

    /*
    Creating Queue the way 2 with queue builder
     */
    @Bean
    Queue example2Queue(){
        return QueueBuilder.durable("Example2Queue")
                .autoDelete()
                .exclusive()
                .build();
    }


}
