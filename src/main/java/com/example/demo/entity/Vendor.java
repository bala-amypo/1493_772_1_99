package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vendor {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String vendorName;

    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Vendor() {}
    // getters and setters
    public Long getId(){ return id; }
    public String getVendorName(){ return vendorName; }
    public void setVendorName(String n){ vendorName=n; }
    public void setContactEmail(String e){ contactEmail=e; }
    public String getContactEmail(){ return contactEmail; }
}
