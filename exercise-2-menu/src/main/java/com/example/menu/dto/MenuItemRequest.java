package com.example.menu.dto;

import com.example.menu.validation.ValidCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class MenuItemRequest {
    
    @NotBlank(message = "Name is required")
    @Pattern(
        regexp = "^[^0-9]+$",
        message = "Name must contain only letters"
    )
    private String name;

    @NotBlank(message = "Category is required")
    @ValidCategory(message = "Category must be Appetizer, Main, Dessert, or Drink")
    private String category;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    private Double price;

    @NotNull(message = "Availability is required")
    private Boolean available;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
