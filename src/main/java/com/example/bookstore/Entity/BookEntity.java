package com.example.bookstore.Entity;

import com.example.bookstore.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    public BookEntity(BookDTO books) {
        this.bookName = books.getBookName();
        this.authorName = books.getAuthorName();
        this.bookDescription = books.getBookDescription();
        this.bookImg = books.getBookImg();
        this.price = books.getPrice();
        this.quantity = books.getQuantity();
    }
}
