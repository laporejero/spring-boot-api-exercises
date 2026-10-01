package com.example.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class EmployeeRequest {

    @NotBlank(message = "First Name is required")
    @Pattern(
        regexp = "^[^0-9]+$",
        message = "First Name must contain only letters"
    )
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Pattern(
        regexp = "^[^0-9]+$",
        message = "Last Name must contain only letters"
    )
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Department is required")
    @Pattern(
        regexp = "^(?=.*\\p{L}).+$",
        message = "Department must contain letters"
    )
    private String department;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
}
