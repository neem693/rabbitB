package com.example.client.demo.controller;

import com.example.client.demo.OrderCreatedEvent;
import com.example.client.demo.dto.SelectItemResultDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class StartController {

    private final StreamBridge streamBridge;

    @GetMapping("/select_item")
    public ResponseEntity<?> start(){

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


        log.info("B에서 할 서비스들 지금 처리중.....");


        return ResponseEntity.ok(
                SelectItemResultDto.builder()
                        .localDateTime(LocalDateTime.now())
                        .result("200")
                        .build()
        );

    }


}
