package com.bookstore.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    
    public BookNotFoundException() {
        super("Book not found");
    }
}