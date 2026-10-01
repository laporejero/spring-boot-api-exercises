package com.example.companyreviews.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {
    
    @NotBlank(message = "Company name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
