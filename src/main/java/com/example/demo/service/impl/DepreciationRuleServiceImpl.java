package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule == null) {
            throw new BadRequestException("Rule payload is required");
        }

        if (rule.getRuleName() == null || rule.getRuleName().isBlank()) {
            throw new BadRequestException("Rule name is required");
        }

        if (ruleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new BadRequestException("Rule name already exists");
        }

        if (rule.getUsefulLifeYears() <= 0) {
            throw new BadRequestException("Useful life years must be greater than zero");
        }

        if (rule.getSalvageValue() < 0) {
            throw new BadRequestException("Salvage value cannot be negative");
        }

        if (rule.getMethod() == null || rule.getMethod().isBlank()) {
            throw new BadRequestException("Depreciation method is required");
        }

        String method = rule.getMethod().toUpperCase();
        if (!"STRAIGHT_LINE".equals(method)
                && !"DECLINING_BALANCE".equals(method)) {
            throw new BadRequestException("Invalid depreciation method");
        }

        rule.setMethod(method);

        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
