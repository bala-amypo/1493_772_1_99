package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import java.util.List;


public interface AssetDisposalRepository extends JpaRepository<AssetDisposal, Long> {
List<AssetDisposal> findByApprovedBy(User approvedBy);
}