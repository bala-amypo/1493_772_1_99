package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DepreciationRule;

public interface DepreciationRuleRepository extends JpaRepository<DepreciationRule, Long> {}
