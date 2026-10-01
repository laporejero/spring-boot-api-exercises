package com.example.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.menu.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    
    List<MenuItem> findByCategory(String category);
}
