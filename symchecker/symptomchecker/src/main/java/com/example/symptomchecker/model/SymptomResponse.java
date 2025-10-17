package com.example.symptomchecker.model;

import lombok.Data;
import java.util.List;

@Data
public class SymptomResponse {
    private List<Condition> conditions;
    private String triage;
    private String disclaimer;

    @Data
    public static class Condition {
        private String name;
        private String likelihood;
        private double confidence;
        private String rationale;
        private List<String> suggestedNextSteps;
    }
}
