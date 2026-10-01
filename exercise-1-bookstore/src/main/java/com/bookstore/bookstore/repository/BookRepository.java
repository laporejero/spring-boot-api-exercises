package com.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}