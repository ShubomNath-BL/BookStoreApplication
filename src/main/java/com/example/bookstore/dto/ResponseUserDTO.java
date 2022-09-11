package com.example.bookstore.dto;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseUserDTO {
    private String message;
    private Object obj;

    public ResponseUserDTO(String string, UserEntity response) {
        this.message = string;
        this.obj = response;
    }

    public ResponseUserDTO(String string1, List<UserEntity> response) {
        this.message = string1;
        this.obj = response;
    }

    public ResponseUserDTO(String string2, Optional<UserEntity> response) {
        this.message = string2;
        this.obj = response;
    }

    public ResponseUserDTO(String string3, String errMsg) {
        this.message = string3;
        this.obj = errMsg;
    }
}
