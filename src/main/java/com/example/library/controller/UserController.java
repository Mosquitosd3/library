package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/all_user")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PatchMapping("/{userId}/add_book/{bookId}")
    public User addBookToUser(@PathVariable Long userId, @PathVariable Long bookId) {
       return userService.addBookToUser(userId, bookId);
    }

    @PatchMapping("/{userId}/book_return/{bookId}")
    public User bookReturn(@PathVariable Long userId, @PathVariable Long bookId) {
        return userService.bookReturn(userId, bookId);
    }
}
