package com.example.bookstore.dto;

import com.example.bookstore.Entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseBookDTO {

    private String message;
    private Object obj;


    public ResponseBookDTO(String string, BookEntity response) {
        this.message = string;
        this.obj = response;
    }

    public ResponseBookDTO(String string1, List<BookEntity> response) {
        this.message = string1;
        this.obj = response;
    }

    public ResponseBookDTO(String string2, Optional<BookEntity> response) {
        this.message = string2;
        this.obj = response;
    }

    public ResponseBookDTO(String string3, String s1) {
        this.message = string3;
        this.obj = s1;
    }
}
