package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BookStatus;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private BookRepository bookRepo;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("John Doe");
        testUser.setBooks(new ArrayList<>());

        testBook = new Book();
        testBook.setId(1L);
        testBook.setAuthor("Author");
        testBook.setStatus(BookStatus.AVAILABLE);
    }

    @Test
    void saveUser_ShouldReturnSavedUser() {
        when(userRepo.save(any(User.class))).thenReturn(testUser);
        User savedUser = userService.saveUser(testUser);
        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
    }

    @Test
    void getAllUser_ShouldReturnUserList() {
        when(userRepo.findAll()).thenReturn(List.of(testUser));
        List<User> users = userService.getAllUser();
        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0).getName());
    }
}