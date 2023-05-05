package com.example.client.demo.consume;

import com.example.client.demo.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class Consume {

    @Bean
    public Consumer<Message<OrderCreatedEvent>> receiveFromProcessing(){
        return message -> {
            String routingKey = message.getHeaders().get(AmqpHeaders.RECEIVED_ROUTING_KEY, String.class);
            OrderCreatedEvent payload = message.getPayload();
            log.info("processing complete");

            log.info("===processing rabbitMQ event Start===");
            log.info("processing rounting key: {}", routingKey);
            log.info("processing id : {}",payload.getOrderId());
            log.info("ammount : {}",payload.getAmount());
            log.info("processing : {}",payload.getProcessing());
            log.info("===processing rabbitMQ event End===");



            return;
        };

    }

}
