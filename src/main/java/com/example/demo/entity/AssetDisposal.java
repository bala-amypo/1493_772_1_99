package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    // -------- GETTERS --------
    public Long getId() {
        return id;
    }

    public Asset getAsset() {
        return asset;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public Double getDisposalValue() {
        return disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // -------- SETTERS --------
    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public void setDisposalDate(LocalDate disposalDate) {
        this.disposalDate = disposalDate;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}
