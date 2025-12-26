package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {
    
    @Autowired private AssetLifecycleEventService eventService;

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent logEvent(@PathVariable Long assetId, @RequestBody AssetLifecycleEvent event) {
        return eventService.logEvent(assetId, event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEvents(@PathVariable Long assetId) {
        return eventService.getEventsForAsset(assetId);
    }
}