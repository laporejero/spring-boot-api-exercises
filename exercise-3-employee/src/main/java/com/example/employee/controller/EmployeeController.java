package com.example.employee.controller;

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

import com.example.employee.dto.EmployeeRequest;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping 
    public ResponseEntity<Employee> createEmployee(
        @Valid @RequestBody EmployeeRequest request
    ) {
        Employee employee = new Employee(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getDepartment()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping 
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeRepository.findByisDeletedFalse();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new EmployeeNotFoundException());

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
        @PathVariable Long id,
        @Valid @RequestBody EmployeeRequest request
    ) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new EmployeeNotFoundException());

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new EmployeeNotFoundException());

        employee.setIsDeleted(true);

        employeeRepository.save(employee);

        return ResponseEntity.noContent().build();
    }
}
