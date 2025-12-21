package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Vendor;
import java.util.Optional;


public interface VendorRepository extends JpaRepository<Vendor, Long> {
Optional<Vendor> findByVendorName(String vendorName);
}