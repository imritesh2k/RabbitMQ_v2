package com.rabbitmq.rabbitmqproject1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMessageListener implements MessageListener {

    @RabbitListener(queues = RabbitMQQueueConfiguration.MESSAGE_QUEUE)
    public void reciveMessage(Employee employee) throws InvalidSalaryException {
        System.out.println("process Recieved Message From RabbitMQ: " + employee);
        if (employee.getSalary() < 100) {
            throw new InvalidSalaryException();
        }
    }

    @Override
    public void onMessage (Message message) {
        try {
            Employee emp = new ObjectMapper().readValue(message.getBody(), Employee.class);
            System.out.println("Recieved Message From RabbitMQ: " + emp);
            reciveMessage(emp);
        } catch (IOException | InvalidSalaryException e) {
            e.printStackTrace();
        }
    }
}
