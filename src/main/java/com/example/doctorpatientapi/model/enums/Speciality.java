package com.example.doctorpatientapi.model.enums;

public enum Speciality {
    ORTHOPAEDIC("Orthopaedic"),
    GYNECOLOGY("Gynecology"),
    DERMATOLOGY("Dermatology"),
    ENT("ENT");
    
    private final String value;
    
    Speciality(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static boolean contains(String value) {
        for (Speciality speciality : Speciality.values()) {
            if (speciality.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
    
    public static Speciality fromValue(String value) {
        for (Speciality speciality : Speciality.values()) {
            if (speciality.getValue().equalsIgnoreCase(value)) {
                return speciality;
            }
        }
        throw new IllegalArgumentException("Invalid speciality value: " + value);
    }
}