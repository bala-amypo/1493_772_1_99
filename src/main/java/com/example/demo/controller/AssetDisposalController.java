package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping("/request/{assetId}")
    public AssetDisposal request(@PathVariable Long assetId,
                                 @RequestBody AssetDisposal disposal) {
        return service.requestDisposal(assetId, disposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approve(@PathVariable Long disposalId,
                                 @PathVariable Long adminId) {
        return service.approveDisposal(disposalId, adminId);
    }
}
