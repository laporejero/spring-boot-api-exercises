package com.example.menu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu.dto.MenuItemRequest;
import com.example.menu.exception.ResourceNotFoundException;
import com.example.menu.repository.MenuItemRepository;
import com.example.menu.validation.ValidCategory;

import jakarta.validation.Valid;

import com.example.menu.model.MenuItem;

@RestController 
@RequestMapping("/api/menu")
public class MenuItemController {
    
    private final MenuItemRepository repository;

    public MenuItemController(MenuItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping 
    public ResponseEntity<MenuItem> createMenuItem(
        @Valid @RequestBody MenuItemRequest request
    ) {
        MenuItem menuItem = new MenuItem(
            request.getName(),
            request.getCategory(),
            request.getPrice(),
            request.getAvailable()
        );

        MenuItem savedMenuItem = repository.save(menuItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMenuItem);
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems(
        @RequestParam(required = false) @ValidCategory String category
    ) {
        List<MenuItem> menuItems;

        if (category != null) {
            menuItems = repository.findByCategory(category);
        } else {
            menuItems = repository.findAll();
        }

        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem menuItem = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Menu item not found"
            ));

        return ResponseEntity.ok(menuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(
        @PathVariable Long id,
        @Valid @RequestBody MenuItemRequest request
    ) {
        MenuItem menuItem = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Menu item not found"
            ));

        menuItem.setName(request.getName());
        menuItem.setCategory(request.getCategory());
        menuItem.setPrice(request.getPrice());
        menuItem.setAvailable(request.getAvailable());

        MenuItem updatedMenuItem = repository.save(menuItem);

        return ResponseEntity.ok(updatedMenuItem);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        MenuItem menuItem = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Menu item not found"
            ));

        menuItem.setAvailable(!menuItem.getAvailable());

        MenuItem updatedMenuItem = repository.save(menuItem);

        return ResponseEntity.ok(updatedMenuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Menu item not found"
            ));

        repository.delete(menuItem);

        return ResponseEntity.noContent().build();
    }
}
