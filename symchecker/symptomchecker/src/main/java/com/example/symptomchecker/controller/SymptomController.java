package com.example.symptomchecker.controller;

import com.example.symptomchecker.model.SymptomRequest;
import com.example.symptomchecker.model.SymptomResponse;
import com.example.symptomchecker.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // allow frontend
public class SymptomController {

    @Autowired
    private SymptomService service;

    @PostMapping("/check")
    public SymptomResponse checkSymptoms(@RequestBody SymptomRequest req) {
        return service.analyzeSymptoms(req);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
