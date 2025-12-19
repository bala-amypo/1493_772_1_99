package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.entity.Asset;
import com.example.demo.repository.*;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {
    @Autowired AssetLifecycleEventRepository repo;
    @Autowired AssetRepository assetRepo;

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event){
        Asset asset = assetRepo.findById(assetId).orElse(null);
        event.setAsset(asset);
        return repo.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId){
        return repo.findByAssetId(assetId);
    }
}
