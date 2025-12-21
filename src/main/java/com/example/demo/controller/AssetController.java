package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;


@RestController
@RequestMapping("/api/assets")
public class AssetController {


private final AssetService service;


public AssetController(AssetService service) {
this.service = service;
}


@PostMapping("/{vendorId}/{ruleId}")
public Asset create(@PathVariable Long vendorId,
@PathVariable Long ruleId,
@RequestBody Asset asset) {
return service.createAsset(vendorId, ruleId, asset);
}


@GetMapping
public List<Asset> all() {
return service.getAllAssets();
}


@GetMapping("/status/{status}")
public List<Asset> byStatus(@PathVariable String status) {
return service.getAssetsByStatus(status);
}


@GetMapping("/{id}")
public Asset one(@PathVariable Long id) {
return service.getAsset(id);
}
}