package com.example.doctorpatientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "Doctor Patient Management API",
    version = "1.0",
    description = "API for managing doctors, patients and suggesting doctors based on symptoms"
))
public class DoctorPatientApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorPatientApiApplication.class, args);
    }
}