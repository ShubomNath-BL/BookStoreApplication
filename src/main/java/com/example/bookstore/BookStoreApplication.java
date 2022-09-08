package com.example.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookStoreApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to Book Store Application");
        SpringApplication.run(BookStoreApplication.class, args);
        log.info("Hello logger........!");
    }

}
