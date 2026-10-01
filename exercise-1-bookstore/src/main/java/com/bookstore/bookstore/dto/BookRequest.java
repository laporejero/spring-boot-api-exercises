package com.bookstore.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BookRequest {
    
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    @Pattern(
        regexp = "^[^0-9]+$",
        message = "Author name invalid"
    )
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Price is required")
    private Double price;

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public Double getPrice() {
        return price;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
