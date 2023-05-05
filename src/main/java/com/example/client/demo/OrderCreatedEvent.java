package com.example.client.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderCreatedEvent implements Serializable {
    private Long orderId;
    private BigDecimal amount;
    private Boolean processing;


    @Builder(toBuilder = false)
    public OrderCreatedEvent(Long orderId, BigDecimal amount, Boolean processing) {
        this.orderId = orderId;
        this.amount = amount;
        this.processing = processing;
    }




    // 생성자, getter, setter 생략
}