package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    @Autowired private AssetLifecycleEventRepository eventRepository;
    @Autowired private AssetRepository assetRepository;

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
        
        if(event.getEventDate().isAfter(java.time.LocalDate.now())) {
            throw new IllegalArgumentException("Future date");
        }
        if(event.getEventType() == null || event.getEventType().trim().isEmpty()) {
            throw new IllegalArgumentException("Type required");
        }
        if(event.getEventDescription() == null || event.getEventDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description required");
        }
        
        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());

        // Simple status update logic if implied by event type (Safe default)
        if("MAINTENANCE".equalsIgnoreCase(event.getEventType())) {
            asset.setStatus("MAINTENANCE");
            assetRepository.save(asset);
        } else if("ACTIVE".equalsIgnoreCase(event.getEventType()) || "REPAIR_COMPLETED".equalsIgnoreCase(event.getEventType())) {
            asset.setStatus("ACTIVE");
            assetRepository.save(asset);
        }

        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        if(!assetRepository.existsById(assetId)) throw new ResourceNotFoundException("Asset not found");
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}