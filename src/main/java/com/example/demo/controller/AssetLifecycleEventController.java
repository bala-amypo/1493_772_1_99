package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService service;

    public AssetLifecycleEventController(AssetLifecycleEventService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent log(@PathVariable Long assetId,
                                   @RequestBody AssetLifecycleEvent event) {
        return service.logEvent(assetId, event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> list(@PathVariable Long assetId) {
        return service.getEventsForAsset(assetId);
    }
}
