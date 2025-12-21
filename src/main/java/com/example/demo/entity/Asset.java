package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.*;


@Entity
@Table(name = "assets")
public class Asset {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true)
private String assetTag;
private String assetName;
private Double purchaseCost;
private LocalDate purchaseDate;
private String status;
private LocalDateTime createdAt;


@ManyToOne
private Vendor vendor;


@ManyToOne
private DepreciationRule depreciationRule;


public Asset() {}


public Long getId() { return id; }
public void setVendor(Vendor v) { this.vendor = v; }
public void setDepreciationRule(DepreciationRule r) { this.depreciationRule = r; }
public void setStatus(String s) { this.status = s; }
public void setCreatedAt(LocalDateTime t) { this.createdAt = t; }
public Double getPurchaseCost() { return purchaseCost; }
public String getAssetTag() { return assetTag; }
}