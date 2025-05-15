package com.example.doctorpatientapi.service;

import com.example.doctorpatientapi.dto.PatientDTO;
import com.example.doctorpatientapi.model.Patient;
import com.example.doctorpatientapi.model.enums.Symptom;
import com.example.doctorpatientapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    
    private final PatientRepository patientRepository;
    
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Symptom symptom;
        
        try {
            symptom = Symptom.fromValue(patientDTO.getSymptom());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid symptom value. Allowed values are: Arthritis, Back Pain, Tissue injuries, Dysmenorrhea, Skin infection, Skin burn, Ear pain");
        }
        
        Patient patient = Patient.builder()
                .name(patientDTO.getName())
                .city(patientDTO.getCity())
                .email(patientDTO.getEmail())
                .phoneNumber(patientDTO.getPhoneNumber())
                .symptom(symptom)
                .build();
        
        Patient savedPatient = patientRepository.save(patient);
        return convertToDTO(savedPatient);
    }
    
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
    
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    
    private PatientDTO convertToDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .city(patient.getCity())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .symptom(patient.getSymptom().getValue())
                .build();
    }
}