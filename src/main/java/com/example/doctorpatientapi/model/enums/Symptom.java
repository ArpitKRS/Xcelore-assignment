package com.example.doctorpatientapi.model.enums;

public enum Symptom {
    // Orthopaedic symptoms
    ARTHRITIS("Arthritis", Speciality.ORTHOPAEDIC),
    BACK_PAIN("Back Pain", Speciality.ORTHOPAEDIC),
    TISSUE_INJURIES("Tissue injuries", Speciality.ORTHOPAEDIC),
    
    // Gynecology symptoms
    DYSMENORRHEA("Dysmenorrhea", Speciality.GYNECOLOGY),
    
    // Dermatology symptoms
    SKIN_INFECTION("Skin infection", Speciality.DERMATOLOGY),
    SKIN_BURN("Skin burn", Speciality.DERMATOLOGY),
    
    // ENT symptoms
    EAR_PAIN("Ear pain", Speciality.ENT);
    
    private final String value;
    private final Speciality speciality;
    
    Symptom(String value, Speciality speciality) {
        this.value = value;
        this.speciality = speciality;
    }
    
    public String getValue() {
        return value;
    }
    
    public Speciality getSpeciality() {
        return speciality;
    }
    
    public static Symptom fromValue(String value) {
        for (Symptom symptom : Symptom.values()) {
            if (symptom.getValue().equalsIgnoreCase(value)) {
                return symptom;
            }
        }
        throw new IllegalArgumentException("Invalid symptom value: " + value);
    }
    
    public static boolean contains(String value) {
        for (Symptom symptom : Symptom.values()) {
            if (symptom.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}