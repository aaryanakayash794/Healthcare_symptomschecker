package com.example.symptomchecker.model;

import lombok.Data;

@Data
public class SymptomRequest {
    private String symptoms;
    private Integer age;
    private String sex;
    private String duration;
}
