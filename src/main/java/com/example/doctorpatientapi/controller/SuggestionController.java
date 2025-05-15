package com.example.doctorpatientapi.controller;

import com.example.doctorpatientapi.service.SuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suggestions")
@Tag(name = "Doctor Suggestions", description = "API for suggesting doctors based on patient symptoms")
public class SuggestionController {
    
    private final SuggestionService suggestionService;
    
    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }
    
    @GetMapping("/patient/{patientId}")
    @Operation(
        summary = "Suggest doctors for a patient",
        description = "Returns a list of doctors that match the patient's symptom and location"
    )
    public ResponseEntity<?> suggestDoctors(@PathVariable Long patientId) {
        Object result = suggestionService.suggestDoctors(patientId);
        return ResponseEntity.ok(result);
    }
}