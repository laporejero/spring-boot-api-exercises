package com.example.companyreviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.companyreviews.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
