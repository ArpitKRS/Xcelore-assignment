package com.example.doctorpatientapi.config;

import com.example.doctorpatientapi.model.Doctor;
import com.example.doctorpatientapi.model.Patient;
import com.example.doctorpatientapi.model.enums.City;
import com.example.doctorpatientapi.model.enums.Speciality;
import com.example.doctorpatientapi.model.enums.Symptom;
import com.example.doctorpatientapi.repository.DoctorRepository;
import com.example.doctorpatientapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DataInitializer(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) {
        // Add sample doctors
        Doctor doctor1 = Doctor.builder()
                .name("Dr. Amit Kumar")
                .city(City.DELHI)
                .email("amit.kumar@example.com")
                .phoneNumber("9876543210")
                .speciality(Speciality.ORTHOPAEDIC)
                .build();

        Doctor doctor2 = Doctor.builder()
                .name("Dr. Priya Sharma")
                .city(City.NOIDA)
                .email("priya.sharma@example.com")
                .phoneNumber("9876543211")
                .speciality(Speciality.GYNECOLOGY)
                .build();

        Doctor doctor3 = Doctor.builder()
                .name("Dr. Rajesh Singh")
                .city(City.FARIDABAD)
                .email("rajesh.singh@example.com")
                .phoneNumber("9876543212")
                .speciality(Speciality.DERMATOLOGY)
                .build();

        Doctor doctor4 = Doctor.builder()
                .name("Dr. Sneha Patel")
                .city(City.DELHI)
                .email("sneha.patel@example.com")
                .phoneNumber("9876543213")
                .speciality(Speciality.ENT)
                .build();

        Doctor doctor5 = Doctor.builder()
                .name("Dr. Vikram Mehta")
                .city(City.DELHI)
                .email("vikram.mehta@example.com")
                .phoneNumber("9876543214")
                .speciality(Speciality.DERMATOLOGY)
                .build();

        doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3, doctor4, doctor5));

        // Add sample patients
        Patient patient1 = Patient.builder()
                .name("Rahul Gupta")
                .city("Delhi")
                .email("rahul.gupta@example.com")
                .phoneNumber("8765432101")
                .symptom(Symptom.ARTHRITIS)
                .build();

        Patient patient2 = Patient.builder()
                .name("Neha Verma")
                .city("Noida")
                .email("neha.verma@example.com")
                .phoneNumber("8765432102")
                .symptom(Symptom.DYSMENORRHEA)
                .build();

        Patient patient3 = Patient.builder()
                .name("Anil Joshi")
                .city("Mumbai")
                .email("anil.joshi@example.com")
                .phoneNumber("8765432103")
                .symptom(Symptom.SKIN_INFECTION)
                .build();

        Patient patient4 = Patient.builder()
                .name("Meera Singh")
                .city("Faridabad")
                .email("meera.singh@example.com")
                .phoneNumber("8765432104")
                .symptom(Symptom.EAR_PAIN)
                .build();

        patientRepository.saveAll(Arrays.asList(patient1, patient2, patient3, patient4));
    }
}