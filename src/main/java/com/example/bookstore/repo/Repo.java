package com.example.bookstore.repo;

import com.example.bookstore.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

}
