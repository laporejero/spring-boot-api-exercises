package com.bookstore.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.repository.BookRepository;

import jakarta.validation.Valid;

import com.bookstore.bookstore.dto.BookRequest;
import com.bookstore.bookstore.exception.BookNotFoundException;

@RestController 
@RequestMapping("/api/books")
public class BookController {
    
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequest request) {
        Book book = new Book(
            request.getTitle(),
            request.getAuthor(),
            request.getIsbn(),
            request.getPrice()
        );

        Book savedBook = bookRepository.save(book);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(savedBook);
    }

    @GetMapping 
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException());

        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
        @PathVariable Long id, 
        @Valid @RequestBody BookRequest request
    ) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException());

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());

        Book savedBook = bookRepository.save(book);

        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException());

        bookRepository.delete(book);

        return ResponseEntity.noContent().build();
    }
}