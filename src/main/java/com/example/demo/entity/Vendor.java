package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "vendors")
public class Vendor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true)
private String vendorName;
private String contactEmail;
private String phone;
private LocalDateTime createdAt;


public Vendor() {}


public Vendor(String vendorName, String contactEmail, String phone) {
this.vendorName = vendorName;
this.contactEmail = contactEmail;
this.phone = phone;
this.createdAt = LocalDateTime.now();
}


public Long getId() { return id; }


public List<Vendor> getContactEmail() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getContactEmail'");
}


public void setCreatedAt(LocalDateTime now) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setCreatedAt'");
}


public String getVendorName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getVendorName'");
}
}