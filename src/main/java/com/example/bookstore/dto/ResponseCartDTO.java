package com.example.bookstore.dto;

import com.example.bookstore.Entity.CartEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseCartDTO {
    private String message;
    private Object obj;

    public ResponseCartDTO(String string, CartEntity response) {
        this.message = string;
        this.obj = response;
    }

    public ResponseCartDTO(String string1, List<CartEntity> response) {
        this.message = string1;
        this.obj = response;
    }

    public ResponseCartDTO(String string2, Optional<CartEntity> response) {
        this.message = string2;
        this.obj = response;
    }

    public ResponseCartDTO(String string3, String s1) {
        this.message = string3;
        this.obj = s1;
    }
}
