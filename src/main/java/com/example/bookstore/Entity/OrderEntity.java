package com.example.bookstore.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    private LocalDate orderDate;
    private int price;
    private int quantity;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId")
    private UserEntity userId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private BookEntity bookId;
    private boolean cancel;

    public OrderEntity(UserEntity userId, BookEntity bookId, int quantity, LocalDate orderDate, int price, boolean cancel) {
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.price = price;
        this.cancel = cancel;
    }
}
