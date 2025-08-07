package com.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    
    @NotNull(message = "Age cannot be null")
    private int age;
    
    @NotNull(message = "Position cannot be null")
    @Size(min = 1, max = 50, message = "Position must be between 1 and 50 characters")
    private String position;
    
    @NotNull(message = "Department cannot be null")
    @Size(min = 1, max = 50, message = "Department must be between 1 and 50 characters")
    private String department;
    
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be a valid email address")
    private String email;
    
    @NotNull(message = "Salary cannot be null")
    private double salary;
}
