package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AssetDisposal {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    public void setAsset(Asset a){ asset=a; }
    public void setDisposalMethod(String m){ disposalMethod=m; }
    public void setDisposalValue(Double v){ disposalValue=v; }
    public void setDisposalDate(LocalDate d){ disposalDate=d; }
    public void setApprovedBy(User u){ approvedBy=u; }
    public Asset getAsset(){ return asset; }
}
