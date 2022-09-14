package com.example.bookstore.repo;

import com.example.bookstore.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM bookstoreapp.orders where orders.orderid=:id", nativeQuery = true)
    void deleteById(int id);
}
