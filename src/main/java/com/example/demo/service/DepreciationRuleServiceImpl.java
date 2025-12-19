package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {
    @Autowired private DepreciationRuleRepository repo;

    @Override
    public DepreciationRule createRule(DepreciationRule r){ return repo.save(r); }
    @Override
    public List<DepreciationRule> getAllRules(){ return repo.findAll(); }
}
