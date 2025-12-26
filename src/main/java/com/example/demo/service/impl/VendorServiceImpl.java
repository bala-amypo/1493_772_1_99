package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        vendorRepository.findByVendorName(vendor.getVendorName())
                .ifPresent(v -> { throw new IllegalArgumentException("Duplicate vendor"); });

        if (vendor.getContactEmail() != null &&
            !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", vendor.getContactEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }

        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendor(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));
    }
}
