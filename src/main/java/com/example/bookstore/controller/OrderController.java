package com.example.bookstore.controller;

import com.example.bookstore.Entity.OrderEntity;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseBookDTO;
import com.example.bookstore.dto.ResponseOrderDTO;
import com.example.bookstore.service.IBookService;
import com.example.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("add")
    public ResponseEntity<ResponseOrderDTO> addOrder(@RequestBody OrderDTO orderDTO){
        OrderEntity orderEntity = orderService.save(orderDTO);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Order has been submitted", orderEntity);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseOrderDTO> getAllOrders(){
        List<OrderEntity> orderEntities = orderService.getAllData();
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("All the orders are:- ", orderEntities);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseOrderDTO> getOrderById(@PathVariable int id){
        Optional<OrderEntity> response = orderService.getById(id);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Orders related to ID are:- ", response);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseOrderDTO> deleteOrderById(@PathVariable int id){
        orderService.deleteData(id);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Order details has been deleted: ", "Deleted id: "+id);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.GONE);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseOrderDTO> updateOrderById(@RequestBody OrderDTO orderDTO, @PathVariable int id){
        OrderEntity response = orderService.updateOrder(orderDTO, id);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Orders after updated are:- ", response);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
}
