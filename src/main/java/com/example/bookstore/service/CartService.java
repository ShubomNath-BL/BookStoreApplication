package com.example.bookstore.service;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.CartEntity;
import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repo.BookRepo;
import com.example.bookstore.repo.CartRepo;
import com.example.bookstore.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepo repository;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    Repo repo;
    @Override
    public CartEntity saveData(CartDTO cart) {
        Optional<UserEntity> user = repo.findById(cart.getUserId());
        Optional<BookEntity> book = bookRepo.findById(cart.getBookId());
        if(user.isPresent() && book.isPresent()){
            CartEntity cartEntity = new CartEntity(user.get(), book.get(),cart.getQuantity());
            repository.save(cartEntity);
            return cartEntity;
        }
        else {
            throw new BookStoreException("Insertion has been failed......!");
        }
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
        Optional<UserEntity> user = repo.findById(cartDTO.getUserId());
        Optional<BookEntity> book = bookRepo.findById(cartDTO.getBookId());
        CartEntity cartEntity = repository.findById(id).orElse(null);
        if(cartEntity!=null){
            cartEntity.setUserId(user.get());
            cartEntity.setBookId(book.get());
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

    @Override
    public Optional<CartEntity> getByUserId(long userId) {
        Optional<CartEntity> cartEntity = repository.findByUserId(userId);
        if(cartEntity.isPresent()){
            return cartEntity;
        }
        else {
            throw new BookStoreException("Book id not found");
        }
    }
}
