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


public void setAsset(Asset a) { this.asset = a; }
public void setApprovedBy(User u) { this.approvedBy = u; }
public void setCreatedAt(LocalDateTime t) { this.createdAt = t; }
public Double getDisposalValue() { return disposalValue; }
public Asset getAsset() { return asset; }
}