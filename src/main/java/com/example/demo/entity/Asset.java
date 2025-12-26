package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String assetTag;

    private String assetName;
    private LocalDate purchaseDate;
    private Double purchaseCost;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private DepreciationRule depreciationRule;

    private String status = "ACTIVE";

    private LocalDateTime createdAt = LocalDateTime.now();

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public String getStatus() {
        return status;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
