package com.example.bookstore.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserException extends RuntimeException{
    private String message;

    public UserException(String string) {
        this.message = string;
    }
}
