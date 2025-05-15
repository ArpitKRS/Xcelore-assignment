package com.example.doctorpatientapi.model;

import com.example.doctorpatientapi.model.enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    
    @NotBlank(message = "City is required")
    @Size(max = 20, message = "City must be at most 20 characters long")
    private String city;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,}", message = "Phone number should have at least 10 digits")
    private String phoneNumber;
    
    @NotNull(message = "Symptom is required")
    @Enumerated(EnumType.STRING)
    private Symptom symptom;
}