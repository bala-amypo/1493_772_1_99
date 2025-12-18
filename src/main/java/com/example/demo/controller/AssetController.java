package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired private AssetService service;

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset create(@PathVariable Long vendorId, @PathVariable Long ruleId, @RequestBody Asset a){
        return service.createAsset(vendorId, ruleId, a);
    }

    @GetMapping public List<Asset> all(){ return service.getAllAssets(); }
    @GetMapping("/{id}") public Asset one(@PathVariable Long id){ return service.getAsset(id); }
    @GetMapping("/status/{status}") public List<Asset> byStatus(@PathVariable String status){
        return service.getAssetsByStatus(status);
    }
}
