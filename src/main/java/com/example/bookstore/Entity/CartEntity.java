package com.example.bookstore.Entity;

import com.example.bookstore.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue
    private int cartId;
    private long userId;
    private int bookId;
    private int quantity;

    public CartEntity(CartDTO cart) {
        this.userId = cart.getUserId();
        this.bookId = cart.getBookId();
        this.quantity = cart.getQuantity();
    }
}
