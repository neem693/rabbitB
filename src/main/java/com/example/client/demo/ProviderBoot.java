package com.example.client.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProviderBoot implements ApplicationRunner {

    private final StreamBridge streamBridge;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //메시지 보내기

        OrderCreatedEvent item = OrderCreatedEvent.builder()
                .amount(new BigDecimal(1))
                .orderId(10L)
                .processing(false)
                .build();

        String routingKey = "order1";
        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(item)
                .setHeader("routingKey", routingKey)
                .build();
        log.info("send start");
        streamBridge.send("selectItem-out-0", message);
        log.info("send complete");

    }
}
