package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;       // Rule name
    private String method;         // Depreciation method (e.g., "SLM")
    private int usefulLifeYears;   // Useful life in years
    private double salvageValue;   // Residual value
    private LocalDateTime createdAt;

    // No-args constructor
    public DepreciationRule() {}

    // All-args constructor
    public DepreciationRule(String ruleName, String method, int usefulLifeYears, double salvageValue) {
        this.ruleName = ruleName;
        this.method = method;
        this.usefulLifeYears = usefulLifeYears;
        this.salvageValue = salvageValue;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public int getUsefulLifeYears() { return usefulLifeYears; }
    public void setUsefulLifeYears(int usefulLifeYears) { this.usefulLifeYears = usefulLifeYears; }

    public double getSalvageValue() { return salvageValue; }
    public void setSalvageValue(double salvageValue) { this.salvageValue = salvageValue; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
