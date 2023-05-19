package com.bjitacademy.securityModule.controllers;


import com.bjitacademy.securityModule.entity.BookEntity;
import com.bjitacademy.securityModule.model.BookRequestModel;
import com.bjitacademy.securityModule.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService ;
    @PostMapping("/add")
    public ResponseEntity<Object> createBooks(@RequestBody BookRequestModel bookRequestModel){
        return bookService.addBooks(bookRequestModel);
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllBooks() {
        //List<BookEntity> books = (List<BookEntity>) bookService.getAllBooks();
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Integer bookId) {
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer bookId) {
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{bookId}")
    public ResponseEntity<Object> updateBook(@PathVariable Integer bookId, @RequestBody BookRequestModel bookRequestModel) {
        return bookService.updateBooks(bookId, bookRequestModel);
    }


    @GetMapping("/author/{authorName}")
    public ResponseEntity<BookEntity> getBookByAuthorName(@PathVariable String authorName) {
        return bookService.getBookByAuthorName(authorName);
    }


    @GetMapping("/author/{authorName}/{bookName}")
    public ResponseEntity<Object> bookDetails(@PathVariable String authorName,@PathVariable String bookName) {
        return bookService.getBookByAuthorName(authorName,bookName);
    }

}

