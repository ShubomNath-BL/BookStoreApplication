package com.example.bookstore.service;

import com.example.bookstore.Entity.BookEntity;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exception.BookStoreException;
import com.example.bookstore.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepo repository;
    @Override
    public BookEntity saveData(BookDTO books) {
        BookEntity bookEntity = new BookEntity(books);
        repository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public List<BookEntity> getAllData() {
        List<BookEntity> bookEntities = repository.findAll();
        return bookEntities;
    }

    @Override
    public Optional<BookEntity> getById(int id) {
        Optional<BookEntity> bookEntity = repository.findById(id);
        if(bookEntity.isPresent()){
            return bookEntity;
        }
        else {
            throw new BookStoreException("Book id not found");
        }
    }

    @Override
    public void deleteData(int id) {
        Optional<BookEntity> bookEntity = repository.findById(id);
        if(bookEntity.isPresent()){
            repository.deleteById(id);
        }else {
            throw new BookStoreException("Id not found....!");
        }
    }

    @Override
    public Optional<BookEntity> getBooksByName(String bookName) {
        Optional<BookEntity> bookEntity = repository.findBooksByBookName(bookName);
        if(bookEntity.isPresent()){
            return bookEntity;
        }
        else {
            throw new BookStoreException("Book not found........!");
        }
    }

    @Override
    public BookEntity editData(BookDTO bookDTO, int id) {
        BookEntity bookEntity = repository.findById(id).orElse(null);
        if(bookEntity!=null){
            bookEntity.setBookName(bookDTO.getBookName());
            bookEntity.setAuthorName(bookDTO.getAuthorName());
            bookEntity.setBookImg(bookDTO.getBookImg());
            bookEntity.setBookDescription(bookDTO.getBookDescription());
            bookEntity.setPrice(bookDTO.getPrice());
            bookEntity.setQuantity(bookDTO.getQuantity());
            repository.save(bookEntity);
            return bookEntity;
        }else{
            throw new BookStoreException("Book id not find");
        }
    }

    @Override
    public List<BookEntity> getAllDataInDescendingOrder(String field) {
        List<BookEntity> bookEntities = repository.findAll(Sort.by(Sort.Direction.DESC, field));
        return bookEntities;
    }

    @Override
    public List<BookEntity> getAllDataInAscendingOrder(String field) {
        List<BookEntity> bookEntities = repository.findAll(Sort.by(Sort.Direction.ASC,field));
        return bookEntities;
    }

    @Override
    public BookEntity editQuantityOfBook(BookDTO bookDTO, String bookName, String authorName) {
        BookEntity bookEntity = repository.findBooksByBookName(bookName).get();
        if(bookName.equals(bookEntity.getBookName()) && authorName.equals(bookEntity.getAuthorName())){
            bookEntity.setQuantity(bookDTO.getQuantity());
            repository.save(bookEntity);
            return bookEntity;
        }else{
            throw new BookStoreException("Book not found.....!");
        }
    }
}
