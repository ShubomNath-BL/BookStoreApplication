package com.example.bookstore.service;

import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserEntity saveData(UserDTO user);

    List<UserEntity> getAllData();

    Optional<UserEntity> getById(long id);

    UserEntity getByEmail(String email);

    UserEntity forgotpassword(UserDTO user, long id);

    UserEntity editData(UserDTO user, String email);

    String addRecord(UserDTO user) throws Exception;


    UserEntity findByToken(String token);

    String loginUser(String email, String password);
}
