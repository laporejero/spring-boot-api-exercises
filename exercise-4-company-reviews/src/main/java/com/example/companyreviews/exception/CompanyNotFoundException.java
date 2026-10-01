package com.example.companyreviews.exception;

public class CompanyNotFoundException extends RuntimeException {
    
    public CompanyNotFoundException() {
        super("Company not found");
    }
}
