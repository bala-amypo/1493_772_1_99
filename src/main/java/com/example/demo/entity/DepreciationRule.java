package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_rules")
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ruleName;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private int usefulLifeYears;

    @Column(nullable = false)
    private double salvageValue;

    private LocalDateTime createdAt = LocalDateTime.now();

    // ---------- Constructors ----------
    public DepreciationRule() {}

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }

    public double getSalvageValue() {
        return salvageValue;
    }

    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
