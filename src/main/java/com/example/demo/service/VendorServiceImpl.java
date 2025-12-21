package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepo;

    public VendorServiceImpl(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        if (vendor.getVendorName() == null || vendor.getVendorName().isBlank())
            throw new IllegalArgumentException("Vendor name required");

        vendorRepo.findByVendorName(vendor.getVendorName())
                .ifPresent(v -> { throw new IllegalArgumentException("Vendor already exists"); });

        if (vendor.getContactEmail() == null || !vendor.getContactEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email");

        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepo.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }
}
