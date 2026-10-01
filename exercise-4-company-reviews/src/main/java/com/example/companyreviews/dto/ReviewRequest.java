package com.example.companyreviews.dto;

import jakarta.validation.constraints.NotBlank;

public class ReviewRequest {
    
    @NotBlank(message = "Invalid comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}