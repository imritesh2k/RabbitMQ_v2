package com.rabbitmq.rabbitlistnerproject;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMQMessageListner implements MessageListener {
   /*
   Reading the messages from the queue
    */
    @Override
    public void onMessage(Message message) {
        //System.out.printf("Message = "+new String(message.getBody()));//Here get body because in rabbitmq messages having headers as well as body also
        System.out.println(message);
    }
}
