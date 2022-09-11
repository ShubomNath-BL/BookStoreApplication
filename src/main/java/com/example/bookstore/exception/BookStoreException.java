package com.example.bookstore.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookStoreException extends RuntimeException{
    private String message;

    public BookStoreException(String string) {
        this.message = string;
    }
}
