package com.example.doctorpatientapi.service;

import com.example.doctorpatientapi.dto.DoctorDTO;
import com.example.doctorpatientapi.model.Doctor;
import com.example.doctorpatientapi.model.Patient;
import com.example.doctorpatientapi.model.enums.City;
import com.example.doctorpatientapi.model.enums.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuggestionService {
    
    private final DoctorService doctorService;
    private final PatientService patientService;
    
    @Autowired
    public SuggestionService(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    
    public Object suggestDoctors(Long patientId) {
        Optional<Patient> patientOptional = patientService.getPatientById(patientId);
        
        if (patientOptional.isEmpty()) {
            return Collections.singletonMap("message", "Patient not found");
        }
        
        Patient patient = patientOptional.get();
        String patientCity = patient.getCity();
        Speciality requiredSpeciality = patient.getSymptom().getSpeciality();
        
        // Check if patient's city is one of the supported cities
        City doctorCity;
        try {
            doctorCity = City.fromValue(patientCity);
        } catch (IllegalArgumentException e) {
            return Collections.singletonMap("message", "We are still waiting to expand to your location");
        }
        
        // Check if there are doctors in the patient's city
        boolean cityHasDoctors = doctorService.existsByCity(doctorCity);
        if (!cityHasDoctors) {
            return Collections.singletonMap("message", "We are still waiting to expand to your location");
        }
        
        // Find doctors with matching speciality in the patient's city
        List<Doctor> matchingDoctors = doctorService.findDoctorsByCityAndSpeciality(doctorCity, requiredSpeciality);
        
        if (matchingDoctors.isEmpty()) {
            return Collections.singletonMap("message", "There isn't any doctor present at your location for your symptom");
        }
        
        // Convert doctors to DTOs
        List<DoctorDTO> suggestedDoctors = matchingDoctors.stream()
                .map(doctor -> DoctorDTO.builder()
                        .id(doctor.getId())
                        .name(doctor.getName())
                        .city(doctor.getCity().getValue())
                        .email(doctor.getEmail())
                        .phoneNumber(doctor.getPhoneNumber())
                        .speciality(doctor.getSpeciality().getValue())
                        .build())
                .collect(Collectors.toList());
        
        return suggestedDoctors;
    }
}