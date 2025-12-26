package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired private VendorRepository vendorRepository;

    @Override
    public Vendor createVendor(Vendor vendor) {
        if(vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new IllegalArgumentException("Vendor exists");
        }
        if(!vendor.getContactEmail().contains("@")) throw new IllegalArgumentException("Invalid email");
        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}