package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.time.*;
import java.util.List;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepo,
                                          AssetRepository assetRepo) {
        this.eventRepo = eventRepo;
        this.assetRepo = assetRepo;
    }

    @Override
    public AssetLifecycleEvent logEvent(Long assetId, AssetLifecycleEvent event) {

        Asset asset = assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (event.getEventType() == null || event.getEventType().isBlank())
            throw new IllegalArgumentException("Event type required");

        if (event.getEventDescription() == null || event.getEventDescription().isBlank())
            throw new IllegalArgumentException("Event description required");

        if (event.getEventDate() == null || event.getEventDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid event date");

        event.setAsset(asset);
        event.setLoggedAt(LocalDateTime.now());

        return eventRepo.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {

        assetRepo.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        return eventRepo.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
