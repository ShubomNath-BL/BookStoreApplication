package com.example.bookstore.Entity;

import com.example.bookstore.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "book_id")
    private BookEntity bookId;
    private int quantity;

    public CartEntity(UserEntity userId, BookEntity bookId, int quantity) {
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

}
