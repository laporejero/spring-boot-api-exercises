package com.example.companyreviews.controller;

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

import com.example.companyreviews.dto.CompanyRequest;
import com.example.companyreviews.exception.CompanyNotFoundException;
import com.example.companyreviews.model.Company;
import com.example.companyreviews.repository.CompanyRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(
        @Valid @RequestBody CompanyRequest request
    ) {
        Company company = new Company(
            request.getName()
        );

        Company savedCompany = companyRepository.save(company);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @GetMapping 
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException());

        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(
        @PathVariable Long id,
        @Valid @RequestBody CompanyRequest request
    ) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException());

        company.setName(request.getName());

        Company updatedCompany = companyRepository.save(company);

        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        Company company = companyRepository.findById(id)
            .orElseThrow(() -> new CompanyNotFoundException());

        companyRepository.delete(company);

        return ResponseEntity.noContent().build();
    }
}
