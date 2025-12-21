package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AssetLifecycleEvent;
import java.util.List;


public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
List<AssetLifecycleEvent> findByAssetIdOrderByEventDateDesc(Long assetId);
}