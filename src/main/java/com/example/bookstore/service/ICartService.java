package com.example.bookstore.service;

import com.example.bookstore.Entity.CartEntity;
import com.example.bookstore.dto.CartDTO;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    CartEntity saveData(CartDTO cart);

    List<CartEntity> recieveList();

    Optional<CartEntity> getById(int id);

    void deleteById(int id);

    CartEntity updateData(CartDTO cartDTO, int id);

    CartEntity updateQuantity(CartDTO cartDTO, int id);

    Optional<CartEntity> getByUserId(long userId);
}
