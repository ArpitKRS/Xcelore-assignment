package com.example.doctorpatientapi.service;

import com.example.doctorpatientapi.dto.DoctorDTO;
import com.example.doctorpatientapi.model.Doctor;
import com.example.doctorpatientapi.model.enums.City;
import com.example.doctorpatientapi.model.enums.Speciality;
import com.example.doctorpatientapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    
    private final DoctorRepository doctorRepository;
    
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        City city;
        Speciality speciality;
        
        try {
            city = City.fromValue(doctorDTO.getCity());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid city value. Allowed values are: Delhi, Noida, Faridabad");
        }
        
        try {
            speciality = Speciality.fromValue(doctorDTO.getSpeciality());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid speciality value. Allowed values are: Orthopaedic, Gynecology, Dermatology, ENT");
        }
        
        Doctor doctor = Doctor.builder()
                .name(doctorDTO.getName())
                .city(city)
                .email(doctorDTO.getEmail())
                .phoneNumber(doctorDTO.getPhoneNumber())
                .speciality(speciality)
                .build();
        
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDTO(savedDoctor);
    }
    
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<DoctorDTO> getDoctorById(Long id) {
        return doctorRepository.findById(id).map(this::convertToDTO);
    }
    
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    
    public List<Doctor> findDoctorsByCityAndSpeciality(City city, Speciality speciality) {
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }
    
    public boolean existsByCity(City city) {
        return doctorRepository.existsByCity(city);
    }
    
    private DoctorDTO convertToDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .city(doctor.getCity().getValue())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhoneNumber())
                .speciality(doctor.getSpeciality().getValue())
                .build();
    }
}