package com.example.doctorpatientapi.dto;

import com.example.doctorpatientapi.model.enums.City;
import com.example.doctorpatientapi.model.enums.Speciality;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
    
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,}", message = "Phone number should have at least 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Speciality is required")
    private String speciality;
}