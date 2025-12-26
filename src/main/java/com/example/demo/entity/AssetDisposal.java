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

    public Long getId() { return id; }
    public Asset getAsset() { return asset; }

    public void setAsset(Asset asset) { this.asset = asset; }
    public void setDisposalValue(Double disposalValue) {
        this.disposalValue = disposalValue;
    }
    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}
