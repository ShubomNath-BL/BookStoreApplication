package com.example.bookstore.controller;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.Entity.UserEntity;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseBookDTO;
import com.example.bookstore.dto.ResponseUserDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstorebooks")
public class BookController {
    @Autowired
    IBookService bookService;
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insertBook(@Valid @RequestBody BookDTO books){
        BookEntity response = bookService.saveData(books);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Data inserted successfully", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllBook(){
        List<BookEntity> response = bookService.getAllData();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("List of all user: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable int id){
        Optional<BookEntity> response = bookService.getById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Books related to id are:- ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBookDTO> deleteUserData(@PathVariable int id){
        bookService.deleteData(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("User details has been deleted: ", "Deleted id: "+id);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.GONE);
    }
    @GetMapping("/searchbookbyname/{bookName}")
    public ResponseEntity<ResponseBookDTO> searchBooksByName(@PathVariable String bookName){
        Optional<BookEntity> response = bookService.getBooksByName(bookName);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Information related to required book is:- ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookbyID(@RequestBody BookDTO bookDTO, @PathVariable int id) {
        BookEntity bookEntity = bookService.editData(bookDTO, id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Edit book details by id: ", bookEntity);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingDesc/{field}")
    public ResponseEntity<ResponseBookDTO> sortingDesc(@PathVariable String field){
        List<BookEntity> response = bookService.getAllDataInDescendingOrder(field);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("List of all books as per higher to lower order: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @GetMapping("/sortingAsc/{field}")
    public ResponseEntity<ResponseBookDTO> sortingAsc(@PathVariable String field){
        List<BookEntity> response = bookService.getAllDataInAscendingOrder(field);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("List of all books as per lower to higher order: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    @PutMapping("/updateQuantity")
    public ResponseEntity<ResponseBookDTO> updateQuantity(@RequestBody BookDTO bookDTO,
                                                          @RequestParam(value = "bookName", defaultValue = "") String bookName,
                                                          @RequestParam(value = "authorName", defaultValue = "") String authorName){
        BookEntity response = bookService.editQuantityOfBook(bookDTO,bookName,authorName);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Book quantity updated: ", response);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
}
