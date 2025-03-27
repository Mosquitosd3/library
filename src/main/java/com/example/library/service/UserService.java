package com.example.library.service;

import com.example.library.exceprion.BookAlreadyTakenException;
import com.example.library.exceprion.NotFoundException;
import com.example.library.model.Book;
import com.example.library.model.BookStatus;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final BookRepository  bookRepo;

    public UserService(UserRepository repository, BookRepository bookRepo) {
        this.userRepo = repository;
        this.bookRepo = bookRepo;
    }

    public User saveUser(User user) {
        if (user == null) {
            return null;
        }
        return userRepo.save(user);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Transactional
    public User addBookToUser(Long userId, Long bookId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found, id = " + userId)
        );
        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book not found, id = " + bookId)
        );

        if (book.getStatus() == BookStatus.BORROWED) {
            throw new BookAlreadyTakenException("Book is already borrowed");
        }

        book.setStatus(BookStatus.BORROWED);
        user.getBooks().add(book);
        bookRepo.save(book);
        return userRepo.save(user);
    }


    @Transactional
    public User bookReturn(Long userId, Long bookId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found, id = " + userId)
        );
        Book book = bookRepo.findById(bookId).orElseThrow(
                () -> new NotFoundException("Book not found, id = " + bookId)
        );

        if (book.getStatus() == BookStatus.AVAILABLE) {
            throw new BookAlreadyTakenException("Book is already available");
        }

        book.setStatus(BookStatus.AVAILABLE);
        user.getBooks().remove(book);

        bookRepo.save(book);
        return userRepo.save(user);
    }
}
