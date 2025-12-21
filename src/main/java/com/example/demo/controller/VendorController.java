package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;


@RestController
@RequestMapping("/api/vendors")
public class VendorController {


private final VendorService service;


public VendorController(VendorService service) {
this.service = service;
}


@PostMapping
public Vendor create(@RequestBody Vendor vendor) {
return service.createVendor(vendor);
}


@GetMapping
public List<Vendor> all() {
return service.getAllVendors();
}
}




