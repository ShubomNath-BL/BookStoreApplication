package com.example.bookstore.service;

import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.exception.UserException;
import com.example.bookstore.repo.Repo;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    Repo repository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService sender;
    @Override
    public UserEntity saveData(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        repository.save(userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllData() {
        List<UserEntity> result = repository.findAll();
        return result;
    }

    @Override
    public Optional<UserEntity> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public UserEntity getByEmail(String email) {
        UserEntity result = repository.findByEmail(email);
        return result;
    }

    @Override
    public UserEntity forgotpassword(UserDTO user, long id) {
        UserEntity userEntity = repository.findById(id).get();
        if(repository.findById(id).isPresent()){
            userEntity.setPassword(user.getPassword());
            repository.save(userEntity);
            return userEntity;
        }
        else {
            throw new UserException("Id not found.....!");
        }
    }

    @Override
    public UserEntity editData(UserDTO user, String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if(userEntity != null){
           userEntity.setFirstName(user.getFirstName());
           userEntity.setLastName(user.getLastName());
           userEntity.setEmail(user.getEmail());
           userEntity.setAddress(user.getAddress());
           userEntity.setPassword(user.getPassword());
           userEntity.setDob(user.getDob());
           repository.save(userEntity);
           return userEntity;
        }
        else {
            throw new UserException("Email not found");
        }
    }

    @Override
    public String addRecord(UserDTO user) throws Exception{
        UserEntity newUser = new UserEntity(user);
        repository.save(newUser);
        String token = tokenUtil.createToken(newUser.getUserId());
        sender.sendEmail(String.valueOf(newUser.getEmail()),"TestMail...!","Hello..."+newUser.getFirstName()+" http://localhost:8080/findByToken/"+token);
        return token;
    }

    @Override
    public UserEntity findByToken(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<UserEntity> userEntity = repository.findById(id);
        if(userEntity.isPresent()){
            return userEntity.get();
        }else {
            throw new UserException("Id not found");
        }
    }
}
