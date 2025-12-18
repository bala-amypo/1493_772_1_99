package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {
    @Autowired DepreciationRuleService service;

    @PostMapping
    public DepreciationRule create(@RequestBody DepreciationRule r){ return service.createRule(r); }

    @GetMapping
    public List<DepreciationRule> all(){ return service.getAllRules(); }
}
