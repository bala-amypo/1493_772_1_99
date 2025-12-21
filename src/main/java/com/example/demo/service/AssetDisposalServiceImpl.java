package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepo;
    private final AssetRepository assetRepo;
    private final UserRepository userRepo;

    public AssetDisposalServiceImpl(AssetDisposalRepository disposalRepo,
                                    AssetRepository assetRepo,
                                    UserRepository userRepo) {
        this.disposalRepo = disposalRepo;
        this.assetRepo = assetRepo;
        this.userRepo = userRepo;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (disposal.getDisposalMethod() == null || disposal.getDisposalMethod().isBlank())
            throw new IllegalArgumentException("Disposal method required");

        if (disposal.getDisposalValue() == null || disposal.getDisposalValue() < 0)
            throw new IllegalArgumentException("Invalid disposal value");

        if (disposal.getDisposalDate() == null)
            throw new IllegalArgumentException("Disposal date required");

        disposal.setAsset(asset);
        disposal.setCreatedAt(LocalDateTime.now());

        return disposalRepo.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepo.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User admin = userRepo.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        disposal.setApprovedBy(admin);
        disposal.getAsset().setStatus("DISPOSED");

        assetRepo.save(disposal.getAsset());
        return disposalRepo.save(disposal);
    }
}
