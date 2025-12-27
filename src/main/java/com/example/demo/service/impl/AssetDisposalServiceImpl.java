package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AssetDisposalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalServiceImpl(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal) {

        if (disposal.getDisposalValue() == null || disposal.getDisposalValue() < 0) {
            throw new BadRequestException("Disposal value must be non-negative");
        }

        if (disposal.getDisposalDate() != null &&
                disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Disposal date cannot be in the future");
        }

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        if (disposal.getApprovedBy() != null) {
            throw new BadRequestException("Disposal already approved");
        }

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isAdmin = admin.getRoles()
                .stream()
                .anyMatch(r -> "ADMIN".equals(r.getName()));

        if (!isAdmin) {
            throw new BadRequestException("Only admin can approve disposal");
        }

        disposal.setApprovedBy(admin);

        Asset asset = disposal.getAsset();
        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return disposalRepository.save(disposal);
    }
}
