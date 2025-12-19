package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {
    @Autowired AssetLifecycleEventService service;

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent log(@PathVariable Long assetId, @RequestBody AssetLifecycleEvent e){
        return service.logEvent(assetId,e);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> list(@PathVariable Long assetId){
        return service.getEventsForAsset(assetId);
    }
}
