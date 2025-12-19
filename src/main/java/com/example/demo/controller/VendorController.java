package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired VendorService vendorService;

    @PostMapping
    public Vendor create(@RequestBody Vendor v){ return vendorService.createVendor(v); }

    @GetMapping
    public List<Vendor> all(){ return vendorService.getAllVendors(); }
}
