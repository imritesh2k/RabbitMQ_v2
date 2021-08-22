package com.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //rabbitTemplate.convertAndSend("Hello from our First Message!!!"); //This message will send to default exchange
        //So message to be delivered in specific queue exchange name and routing key is must required as this exchange is
        //binded to specific queue
        //rabbitTemplate.convertAndSend("TestExchange","testRouting","Hello from the code!!!");
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setName("SimpleName");
        simpleMessage.setDescription("SimpleDescription");
        //rabbitTemplate.convertAndSend("TestExchange","testRouting",simpleMessage);
        rabbitTemplate.convertAndSend("MyTopicExchange","exchangeRoutingKey",simpleMessage);
    }
}
