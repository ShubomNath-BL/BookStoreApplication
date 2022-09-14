package com.example.bookstore.dto;

import com.example.bookstore.Entity.OrderEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseOrderDTO {
    private String message;
    private Object obj;

    public ResponseOrderDTO(String message, OrderEntity orderEntity) {
        this.message = message;
        this.obj = orderEntity;
    }

    public ResponseOrderDTO(String message, List<OrderEntity> orderEntities) {
        this.message = message;
        this.obj = orderEntities;
    }

    public ResponseOrderDTO(String message, Optional<OrderEntity> response) {
        this.message = message;
        this.obj = response;
    }

    public ResponseOrderDTO(String message, String s1) {
        this.message = message;
        this.obj = s1;
    }
}
