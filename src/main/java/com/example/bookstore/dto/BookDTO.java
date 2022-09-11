package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Invalid Book Name")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = "Invalid Author Name")
    private String authorName;
    @NotBlank(message = "Description should not be blank")
    private String bookDescription;
    private String bookImg;
    @NotNull(message = "Price should not be null")
    private int price;
    @NotNull(message = "Quantity should not be null")
    private int quantity;
}
