package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Asset> getAll() {
        return service.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset get(@PathVariable Long id) {
        return service.getAsset(id);
    }

    @GetMapping("/status/{status}")
    public List<Asset> byStatus(@PathVariable String status) {
        return service.getAssetsByStatus(status);
    }
}
