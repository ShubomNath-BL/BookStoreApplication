package com.example.bookstore.controller;

import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookstore")
public class UserController {

    @Autowired
    IUserService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> insertUser(@Valid @RequestBody UserDTO user){
        UserEntity response = service.saveData(user);
        ResponseDTO responseDTO = new ResponseDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUser(){
        List<UserEntity> response = service.getAllData();
        ResponseDTO responseDTO = new ResponseDTO("List of all user: ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable long id){
        Optional<UserEntity> response = service.getById(id);
        ResponseDTO responseDTO = new ResponseDTO("User related to id are:- ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/GetbyEmailID/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable String email){
        UserEntity response = service.getByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO("User related to email id are:- ", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody UserDTO user, @PathVariable long id){
        UserEntity userEntity = service.forgotpassword(user, id);
        ResponseDTO responseDTO = new ResponseDTO("Password has been changed:- ", userEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateuserbyEmail/{email}")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO user, @PathVariable String email) {
        UserEntity userEntity = service.editData(user, email);
        ResponseDTO responseDTO = new ResponseDTO("Edit user data using email: ", userEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/post")
    public ResponseEntity<String> addData(@Valid @RequestBody UserDTO user) throws Exception{
        String token = service.addRecord(user);
        ResponseDTO responseDTO = new ResponseDTO("Record added successfully", token);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("findByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token){
        UserEntity addressEntity = service.findByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved by id successfully:-", addressEntity);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
