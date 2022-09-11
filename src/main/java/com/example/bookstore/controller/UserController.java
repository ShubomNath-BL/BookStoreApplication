package com.example.bookstore.controller;

import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.ResponseUserDTO;
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
@RequestMapping("/bookstoreuser")
public class UserController {

    @Autowired
    IUserService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> insertUser(@Valid @RequestBody UserDTO user){
        UserEntity response = service.saveData(user);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUserDTO> getAllUser(){
        List<UserEntity> response = service.getAllData();
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("List of all user: ", response);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable long id){
        Optional<UserEntity> response = service.getById(id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User related to id are:- ", response);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @GetMapping("/GetbyEmailID/{email}")
    public ResponseEntity<ResponseUserDTO> getUserByEmailId(@PathVariable String email){
        UserEntity response = service.getByEmail(email);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User related to email id are:- ", response);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<ResponseUserDTO> changePassword(@Valid @RequestBody UserDTO user, @PathVariable long id){
        UserEntity userEntity = service.forgotpassword(user, id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Password has been changed:- ", userEntity);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @PutMapping("/updateuserbyEmail/{email}")
    public ResponseEntity<ResponseUserDTO> updateUser(@RequestBody UserDTO user, @PathVariable String email) {
        UserEntity userEntity = service.editData(user, email);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Edit user data using email: ", userEntity);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/post")
    public ResponseEntity<String> addData(@Valid @RequestBody UserDTO user) throws Exception{
        String token = service.addRecord(user);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Record added successfully", token);
        return new ResponseEntity(responseUserDTO, HttpStatus.CREATED);
    }
    @GetMapping("/findByToken/{token}")
    public ResponseEntity<ResponseUserDTO> findByToken(@PathVariable String token){
        UserEntity userEntity = service.findByToken(token);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Record retrieved by id successfully:-", userEntity);
        return new ResponseEntity(responseUserDTO, HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<ResponseUserDTO> loginWithUser(@RequestParam(value = "email", defaultValue = "") String email,
                                                         @RequestParam(value = "password", defaultValue = "") String password){
        String userEntity = service.loginUser(email, password);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User logged in successfully ", "Welcome message");
        return new ResponseEntity(responseUserDTO, HttpStatus.OK);
    }
}
