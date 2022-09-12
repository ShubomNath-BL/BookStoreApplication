package com.example.bookstore.controller;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.CartEntity;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseBookDTO;
import com.example.bookstore.dto.ResponseCartDTO;
import com.example.bookstore.service.ICartService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstorecart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseCartDTO> insert(@Valid @RequestBody CartDTO cart){
        CartEntity response = cartService.saveData(cart);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<ResponseCartDTO> getAllData(){
        List<CartEntity> response = cartService.recieveList();
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("All data in the cart", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseCartDTO> getById(@PathVariable int id){
        Optional<CartEntity> response = cartService.getById(id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Books related to id are:- ", response);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCartDTO> delete(@PathVariable int id){
        cartService.deleteById(id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Data hsa been deleted:- ", "Deleted id: "+id);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseCartDTO> updateByID(@RequestBody CartDTO cartDTO, @PathVariable int id){
        CartEntity cartEntity = cartService.updateData(cartDTO, id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Data after updated are:- ", cartEntity);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
    @PutMapping("/editquantity/{id}")
    public ResponseEntity<ResponseCartDTO> updateQuantity(@RequestBody CartDTO cartDTO, @PathVariable int id){
        CartEntity cartEntity = cartService.updateQuantity(cartDTO, id);
        ResponseCartDTO responseCartDTO = new ResponseCartDTO("Data after updated are:- ", cartEntity);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.OK);
    }
}
