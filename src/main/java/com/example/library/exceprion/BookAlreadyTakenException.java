package com.example.library.exceprion;

public class BookAlreadyTakenException extends RuntimeException {
    public BookAlreadyTakenException(String message) {
        super(message);
    }
}
