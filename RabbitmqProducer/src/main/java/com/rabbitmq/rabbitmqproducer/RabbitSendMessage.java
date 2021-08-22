package com.rabbitmq.rabbitmqproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitSendMessage implements CommandLineRunner {

    public static final String MESSAGE_EXCHANGE = "ritesh-message-exchange";
    public static final String MESSAGE_ROUTING_KEY = "ritesh-message-routing-key";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setEmpId("101");
        employee.setEmpName("Employee Name");
        employee.setSalary(10);

        Message message = MessageBuilder.withBody(new ObjectMapper().writeValueAsBytes(employee))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        rabbitTemplate.convertAndSend(MESSAGE_EXCHANGE, MESSAGE_ROUTING_KEY, message);
    }

    @Override
    public void run (String... args) throws Exception {
        sendMessage();
    }
}
