package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired private AssetRepository assetRepo;
    @Autowired private VendorRepository vendorRepo;
    @Autowired private DepreciationRuleRepository ruleRepo;

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset){
        asset.setVendor(vendorRepo.findById(vendorId).orElse(null));
        asset.setDepreciationRule(ruleRepo.findById(ruleId).orElse(null));
        return assetRepo.save(asset);
    }

    @Override public List<Asset> getAssetsByStatus(String status){ return assetRepo.findByStatus(status); }
    @Override public List<Asset> getAllAssets(){ return assetRepo.findAll(); }
    @Override public Asset getAsset(Long id){ return assetRepo.findById(id).orElse(null); }
}
