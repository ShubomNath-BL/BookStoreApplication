package com.example.bookstore.service;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.CartEntity;
import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepo repository;
    @Override
    public CartEntity saveData(CartDTO cart) {
        CartEntity cartEntity = new CartEntity(cart);
        repository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public List<CartEntity> recieveList() {
        List<CartEntity> cartEntities = repository.findAll();
        return cartEntities;
    }

    @Override
    public Optional<CartEntity> getById(int id) {
        Optional<CartEntity> cartEntity = repository.findById(id);
        if(cartEntity.isPresent()){
            return cartEntity;
        }
        else {
            throw new BookStoreException("Book id not found");
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<CartEntity> cartEntity = repository.findById(id);
        if(cartEntity.isPresent()){
            repository.deleteById(id);
        }else {
            throw new BookStoreException("Id not found....!");
        }
    }

    @Override
    public CartEntity updateData(CartDTO cartDTO, int id) {
        CartEntity cartEntity = repository.findById(id).orElse(null);
        if(cartEntity!=null){
            cartEntity.setUserId(cartDTO.getUserId());
            cartEntity.setBookId(cartDTO.getBookId());
            cartEntity.setQuantity(cartDTO.getQuantity());
            repository.save(cartEntity);
            return cartEntity;
        }
        else {
            throw new BookStoreException("Cart id not found......!");
        }
    }

    @Override
    public CartEntity updateQuantity(CartDTO cartDTO, int id) {
        CartEntity cartEntity1 = repository.findById(id).orElse(null);
        if(cartEntity1!=null){
            cartEntity1.setQuantity(cartDTO.getQuantity());
            repository.save(cartEntity1);
            return cartEntity1;
        }
        else {
            throw new BookStoreException("Cart id not found......!");
        }
    }
}
