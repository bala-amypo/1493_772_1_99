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

    public Long getId() { return id; }
    public String getAssetTag() { return assetTag; }

    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setDepreciationRule(DepreciationRule rule) {
        this.depreciationRule = rule;
    }
    public void setStatus(String status) { this.status = status; }
}
