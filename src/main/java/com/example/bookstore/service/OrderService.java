package com.example.bookstore.service;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.CartEntity;
import com.example.bookstore.Entity.OrderEntity;
import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repo.BookRepo;
import com.example.bookstore.repo.OrderRepo;
import com.example.bookstore.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    Repo repo;
    @Override
    public OrderEntity save(OrderDTO orderDTO) {
        Optional<UserEntity> user = repo.findById(orderDTO.getUserId());
        Optional<BookEntity> book = bookRepo.findById(orderDTO.getBookId());
        if(user.isPresent() && book.isPresent()){
            OrderEntity orderEntity = new OrderEntity(user.get(), book.get(),orderDTO.getQuantity(),orderDTO.getOrderDate(),orderDTO.getPrice(),orderDTO.isCancel());
            orderRepo.save(orderEntity);
            return orderEntity;
        }
        else{
            throw new BookStoreException("Data not found...........!");
        }
    }

    @Override
    public List<OrderEntity> getAllData() {
        List<OrderEntity> orders = orderRepo.findAll();
        return orders;
    }

    @Override
    public Optional<OrderEntity> getById(int id) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(id);
        if(orderEntity.isPresent()){
            return orderEntity;
        }
        else {
            throw new BookStoreException("Order id not found");
        }
    }

    @Override
    public void deleteData(int id) {
        Optional<OrderEntity> orderEntity = orderRepo.findById(id);
        if(orderEntity.isPresent()){
            orderRepo.deleteById(id);
        }else {
            throw new BookStoreException("Order not found");
        }
    }

    @Override
    public OrderEntity updateOrder(OrderDTO orderDTO, int id) {
        Optional<UserEntity> user = repo.findById(orderDTO.getUserId());
        Optional<BookEntity> book = bookRepo.findById(orderDTO.getBookId());
        OrderEntity orderEntity = orderRepo.findById(id).orElse(null);
        if (orderEntity!=null){
            orderEntity.setUserId(user.get());
            orderEntity.setBookId(book.get());
            orderEntity.setOrderDate(orderDTO.getOrderDate());
            orderEntity.setQuantity(orderDTO.getQuantity());
            orderEntity.setPrice(orderDTO.getPrice());
            orderEntity.setCancel(orderDTO.isCancel());
            orderRepo.save(orderEntity);
            return orderEntity;
        }else {
            throw new BookStoreException("Order not found......!");
        }
    }
}
