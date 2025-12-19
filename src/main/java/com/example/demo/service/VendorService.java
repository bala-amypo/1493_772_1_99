package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Vendor;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    List<Vendor> getAllVendors();
}
