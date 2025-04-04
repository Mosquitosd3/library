package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndStatus(Long id, BookStatus status);
}
