package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DepreciationRule;
import java.util.Optional;


public interface DepreciationRuleRepository extends JpaRepository<DepreciationRule, Long> {
Optional<DepreciationRule> findByRuleName(String ruleName);
}