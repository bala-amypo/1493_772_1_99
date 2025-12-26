package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    @Autowired private DepreciationRuleRepository ruleRepository;

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {
        if(rule.getUsefulLifeYears() <= 0) throw new IllegalArgumentException("Life years > 0");
        if(rule.getSalvageValue() < 0) throw new IllegalArgumentException("Salvage >= 0");
        if(!rule.getMethod().equals("STRAIGHT_LINE") && !rule.getMethod().equals("DECLINING_BALANCE")) {
            throw new IllegalArgumentException("Invalid method");
        }
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}