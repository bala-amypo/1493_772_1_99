package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository repo;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule.getRuleName() == null || rule.getRuleName().isBlank())
            throw new IllegalArgumentException("Rule name required");

        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0)
            throw new IllegalArgumentException("Invalid useful life");

        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0)
            throw new IllegalArgumentException("Invalid salvage value");

        rule.setCreatedAt(LocalDateTime.now());
        return repo.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return repo.findAll();
    }
}
