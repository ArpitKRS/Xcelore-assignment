package com.example.doctorpatientapi.model.enums;

public enum City {
    DELHI("Delhi"),
    NOIDA("Noida"),
    FARIDABAD("Faridabad");
    
    private final String value;
    
    City(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static boolean contains(String value) {
        for (City city : City.values()) {
            if (city.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
    
    public static City fromValue(String value) {
        for (City city : City.values()) {
            if (city.getValue().equalsIgnoreCase(value)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Invalid city value: " + value);
    }
}