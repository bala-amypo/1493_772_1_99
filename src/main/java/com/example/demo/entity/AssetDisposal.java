package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;
    private LocalDateTime createdAt;

    @OneToOne
    private Asset asset;

    @ManyToOne
    private User approvedBy;

    public AssetDisposal() {}

    // ✅ GETTERS
    public String getDisposalMethod() {
        return disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public Asset getAsset() {
        return asset;
    }

    // ✅ SETTERS
    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
