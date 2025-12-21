package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "depreciation_rules")
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleName;
    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;

    public DepreciationRule() {}

    // ✅ GETTERS
    public String getRuleName() {
        return ruleName;
    }

    public Integer getUsefulLifeYears() {
        return usefulLifeYears;
    }

    public Double getSalvageValue() {
        return salvageValue;
    }

    // ✅ SETTER
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
