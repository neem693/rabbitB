package com.example.client.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SelectItemResultDto {

    String result;
    LocalDateTime localDateTime;

    @Builder(toBuilder = false)
    public SelectItemResultDto(String result, LocalDateTime localDateTime) {
        this.result = result;
        this.localDateTime = localDateTime;
    }

}
