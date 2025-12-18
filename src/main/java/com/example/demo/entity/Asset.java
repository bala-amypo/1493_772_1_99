package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Asset {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String assetTag;

    private String assetName;

    @ManyToOne
    private Vendor vendor;

    private LocalDate purchaseDate;
    private Double purchaseCost;

    @ManyToOne
    private DepreciationRule depreciationRule;

    private String status = "ACTIVE";
    private LocalDateTime createdAt = LocalDateTime.now();

    public Asset() {}
    public Long getId(){ return id; }
    public void setVendor(Vendor v){ vendor=v; }
    public void setDepreciationRule(DepreciationRule r){ depreciationRule=r; }
    public void setAssetTag(String t){ assetTag=t; }
    public void setAssetName(String n){ assetName=n; }
    public void setPurchaseCost(Double c){ purchaseCost=c; }
    public void setPurchaseDate(LocalDate d){ purchaseDate=d; }
    public void setStatus(String s){ status=s; }
}
