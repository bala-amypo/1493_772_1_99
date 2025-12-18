package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Asset;

public interface AssetService {
    Asset createAsset(Long vendorId, Long ruleId, Asset asset);
    List<Asset> getAssetsByStatus(String status);
    List<Asset> getAllAssets();
    Asset getAsset(Long id);
}
