package com.example.bookstore.repo;

import com.example.bookstore.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Integer> {
    @Query(value = "SELECT * FROM bookstoreapp.cart where cart.user_id=:userId", nativeQuery = true)
    Optional<CartEntity> findByUserId(long userId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bookstoreapp.cart where cart.cart_id=:id", nativeQuery = true)
    void deleteById(int id);
}
