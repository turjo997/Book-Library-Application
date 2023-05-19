package com.bjitacademy.securityModule.service;

import com.bjitacademy.securityModule.entity.BookEntity;
import com.bjitacademy.securityModule.model.BookRequestModel;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<Object> addBooks(BookRequestModel bookRequestModel);

    ResponseEntity<Object> getAllBooks();

    ResponseEntity<BookEntity> getBookById(Integer bookId);
    ResponseEntity<BookEntity> getBookByAuthorName(String authorName);



    ResponseEntity<BookEntity> deleteBookById(Integer bookId);

    ResponseEntity<Object> updateBooks(Integer id , BookRequestModel bookRequestModel);

    ResponseEntity<Object> getBookByAuthorName(String authorName, String bookName);
}
