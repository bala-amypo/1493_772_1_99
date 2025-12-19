package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class AssetDisposalServiceImpl implements AssetDisposalService {
    @Autowired AssetDisposalRepository repo;
    @Autowired AssetRepository assetRepo;
    @Autowired UserRepository userRepo;

    @Override
    public AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal){
        Asset asset = assetRepo.findById(assetId).orElse(null);
        disposal.setAsset(asset);
        asset.setStatus("DISPOSED");
        assetRepo.save(asset);
        return repo.save(disposal);
    }

    @Override
    public AssetDisposal approveDisposal(Long disposalId, Long adminId){
        AssetDisposal d = repo.findById(disposalId).orElse(null);
        User admin = userRepo.findById(adminId).orElse(null);
        d.setApprovedBy(admin);
         d.getAsset().setStatus("DISPOSED");  // ensure asset status updated
        assetRepo.save(d.getAsset());
        return repo.save(d);
    }
}