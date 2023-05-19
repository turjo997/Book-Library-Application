package com.bjitacademy.securityModule.repository;

import com.bjitacademy.securityModule.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    // List<BookEntity> findByAuthorName(String authorName);

    BookEntity findByAuthorName(String authorName);
    BookEntity findByAuthorNameAndBookName(String authorName,String bookName);
}
