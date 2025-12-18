package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AssetLifecycleEvent;

public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
    List<AssetLifecycleEvent> findByAssetId(Long assetId);
}
