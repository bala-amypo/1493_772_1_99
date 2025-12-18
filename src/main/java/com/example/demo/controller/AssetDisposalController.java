package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService service;

    @PostMapping("/request/{assetId}")
    public AssetDisposal requestDisposal(@PathVariable Long assetId, @RequestBody AssetDisposal disposal) {
        return service.requestDisposal(assetId, disposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(@PathVariable Long disposalId, @PathVariable Long adminId) {
        return service.approveDisposal(disposalId, adminId);
    }
}
