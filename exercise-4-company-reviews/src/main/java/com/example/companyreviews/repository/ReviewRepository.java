package com.example.companyreviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.companyreviews.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByCompanyId(Long companyId);
}