package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.AssetLifecycleEvent;

public interface AssetLifecycleEventService {
    AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event);
    List<AssetLifecycleEvent> getEventsForAsset(Long assetId);
}
