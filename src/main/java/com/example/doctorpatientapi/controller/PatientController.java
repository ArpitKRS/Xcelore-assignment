package com.example.doctorpatientapi.controller;

import com.example.doctorpatientapi.dto.PatientDTO;
import com.example.doctorpatientapi.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Validated
@Tag(name = "Patient Management", description = "APIs for managing patients")
public class PatientController {
    
    private final PatientService patientService;
    
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    
    @PostMapping
    @Operation(summary = "Add a new patient", description = "Creates a new patient with the provided details")
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Get all patients", description = "Returns a list of all patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Returns a patient by their ID")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(patient -> {
                    PatientDTO dto = PatientDTO.builder()
                            .id(patient.getId())
                            .name(patient.getName())
                            .city(patient.getCity())
                            .email(patient.getEmail())
                            .phoneNumber(patient.getPhoneNumber())
                            .symptom(patient.getSymptom().getValue())
                            .build();
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Deletes a patient by their ID")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}