package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DepreciationRule {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String ruleName;

    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt = LocalDateTime.now();

    public DepreciationRule() {}
    public Long getId(){ return id; }
    public void setRuleName(String n){ ruleName=n; }
    public void setMethod(String m){ method=m; }
}
