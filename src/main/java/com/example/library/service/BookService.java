package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book saveBook(Book book) {
        if (book == null) {
            return null;
        }
        return bookRepo.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepo.findAll();
    }
}
