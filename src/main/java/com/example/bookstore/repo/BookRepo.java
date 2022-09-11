package com.example.bookstore.repo;

import com.example.bookstore.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<BookEntity, Integer> {
    Optional<BookEntity> findBooksByBookName(String bookName);
}
