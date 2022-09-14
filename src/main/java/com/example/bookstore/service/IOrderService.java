package com.example.bookstore.service;

import com.example.bookstore.Entity.OrderEntity;
import com.example.bookstore.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderEntity save(OrderDTO orderDTO);

    List<OrderEntity> getAllData();

    Optional<OrderEntity> getById(int id);

    void deleteData(int id);

    OrderEntity updateOrder(OrderDTO orderDTO, int id);
}
