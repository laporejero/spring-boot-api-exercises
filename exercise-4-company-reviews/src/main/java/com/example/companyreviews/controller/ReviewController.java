package com.example.companyreviews.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.companyreviews.dto.ReviewRequest;
import com.example.companyreviews.exception.CompanyNotFoundException;
import com.example.companyreviews.model.Company;
import com.example.companyreviews.model.Review;
import com.example.companyreviews.repository.CompanyRepository;
import com.example.companyreviews.repository.ReviewRepository;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/companies/{companyId}/reviews")
public class ReviewController {
    
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public ReviewController(
        ReviewRepository reviewRepository,
        CompanyRepository companyRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(
        @PathVariable Long companyId,
        @Valid @RequestBody ReviewRequest request
    ) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new CompanyNotFoundException());

        Review review = new Review(request.getComment(), company);

        Review savedReview = reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping 
    public ResponseEntity<List<Review>> getReviewsByCompany(@PathVariable Long companyId) {
        companyRepository.findById(companyId)
            .orElseThrow(() -> new CompanyNotFoundException());

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return ResponseEntity.ok(reviews);
    }
}