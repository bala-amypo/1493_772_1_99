package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class VendorServiceImpl implements VendorService {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@(.+)$";

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        if (vendor == null) {
            throw new BadRequestException("Vendor payload is required");
        }

        if (vendor.getVendorName() == null || vendor.getVendorName().isBlank()) {
            throw new BadRequestException("Vendor name is required");
        }

        if (vendorRepository.findByVendorName(vendor.getVendorName()).isPresent()) {
            throw new BadRequestException("Vendor name already exists");
        }

        if (vendor.getContactEmail() == null ||
            !Pattern.matches(EMAIL_REGEX, vendor.getContactEmail())) {
            throw new BadRequestException("Valid contact email is required");
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
