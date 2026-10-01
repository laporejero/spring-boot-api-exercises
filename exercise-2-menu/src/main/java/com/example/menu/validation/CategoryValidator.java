package com.example.menu.validation;

import com.example.menu.model.Category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }

        try {
            Category.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}
