package com.example.bookstore.repo;

import com.example.bookstore.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {
//    @Query(value = "select * from bookstoreapp.cart,bookstoreapp.books,bookstoreapp.user where cart.book_id=books.id and cart.user_id=user.user_id", nativeQuery = true)
//    Optional<CartEntity> findById(int id);
}
