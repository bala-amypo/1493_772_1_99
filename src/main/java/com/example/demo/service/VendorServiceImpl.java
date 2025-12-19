package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired private VendorRepository vendorRepo;

    @Override
    public Vendor createVendor(Vendor v){ return vendorRepo.save(v); }

    @Override
    public List<Vendor> getAllVendors(){ return vendorRepo.findAll(); }
}
