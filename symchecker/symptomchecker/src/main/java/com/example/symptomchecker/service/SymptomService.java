package com.example.symptomchecker.service;

import com.example.symptomchecker.model.SymptomRequest;
import com.example.symptomchecker.model.SymptomResponse;
import com.example.symptomchecker.model.SymptomResponse.Condition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SymptomService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String DISCLAIMER =
        "Educational only: This is not medical advice. Seek a qualified health professional if symptoms persist or worsen.";

    public SymptomResponse analyzeSymptoms(SymptomRequest req) {

        String prompt = String.format(
            "You are a medical information assistant for educational purposes only.\n" +
            "User symptoms: %s\nAge: %d\nSex: %s\nDuration: %s.\n" +
            "Suggest up to 3 possible conditions with likelihood, rationale, and next steps. " +
            "Always include disclaimer.",
            req.getSymptoms(), req.getAge(), req.getSex(), req.getDuration()
        );

        // --- Call OpenAI API (optional, currently mock) ---
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/responses";

        // Use Map instead of JSONObject
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-5-mini");
        requestBody.put("input", prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.postForObject(url, entity, String.class);

        // --- Fallback mock data ---
        SymptomResponse result = new SymptomResponse();
        List<Condition> conditions = new ArrayList<>();

        Condition c1 = new Condition();
        c1.setName("Common Cold");
        c1.setLikelihood("Moderate");
        c1.setConfidence(0.6);
        c1.setRationale("Symptoms of mild fever and sore throat often indicate common viral infection.");
        c1.setSuggestedNextSteps(Arrays.asList("Rest", "Hydration", "Consult doctor if >5 days"));

        conditions.add(c1);
        result.setConditions(conditions);
        result.setTriage("routine");
        result.setDisclaimer(DISCLAIMER);

        return result;
    }
}
