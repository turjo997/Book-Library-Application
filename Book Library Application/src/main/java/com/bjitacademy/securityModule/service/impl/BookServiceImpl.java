package com.bjitacademy.securityModule.service.impl;

import com.bjitacademy.securityModule.entity.BookEntity;
import com.bjitacademy.securityModule.model.BookRequestModel;
import com.bjitacademy.securityModule.repository.BookRepository;
import com.bjitacademy.securityModule.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<Object> addBooks(BookRequestModel bookRequestModel) {
        BookEntity bookEntity = BookEntity.builder()
                .bookName(bookRequestModel.getBookName())
                .authorName(bookRequestModel.getAuthorName())
                .price(bookRequestModel.getPrice())
                .quantity(bookRequestModel.getQuantity())
                .build();

        bookRepository.save(bookEntity);

        return new ResponseEntity<>(bookEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllBooks() {
        //return (ResponseEntity<Object>) bookRepository.findAll();

        List<BookEntity> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }


    @Override
    public ResponseEntity<BookEntity> getBookById(Integer bookId) {
        Optional<BookEntity> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            return ResponseEntity.ok(optionalBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<BookEntity> getBookByAuthorName(String authorName) {
        BookEntity book = bookRepository.findByAuthorName(authorName);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity deleteBookById(Integer bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }



    @Override
    public ResponseEntity<Object> updateBooks(Integer bookId , BookRequestModel bookRequestModel) {
        Optional<BookEntity> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            BookEntity bookEntity = optionalBook.get();
            // Update the book entity with the new values from the request model
            bookEntity.setBookName(bookRequestModel.getBookName());
            bookEntity.setAuthorName(bookRequestModel.getAuthorName());
            bookEntity.setPrice(bookRequestModel.getPrice());
            bookEntity.setQuantity(bookRequestModel.getQuantity());

            // Save the updated book entity
            BookEntity updatedBook = bookRepository.save(bookEntity);

            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getBookByAuthorName(String authorName, String bookName) {
        BookEntity bookEntity = bookRepository.findByAuthorNameAndBookName(authorName, bookName);
        if (bookEntity != null) {
            return new ResponseEntity<>(bookEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(new RuntimeException("Doesn't match authorName and book Name"), HttpStatus.BAD_GATEWAY);
    }
}
