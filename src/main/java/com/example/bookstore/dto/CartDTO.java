package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {
    private long userId;
    private int bookId;
    private int quantity;
}
