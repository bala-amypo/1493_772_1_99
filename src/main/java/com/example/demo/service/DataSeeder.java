package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private DepreciationRuleRepository ruleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }

        Role adminRole = roleRepository.findByName("ADMIN").get();
        Role userRole = roleRepository.findByName("USER").get();

        if(userRepository.findByEmail("integration_admin@example.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("integration_admin@example.com");
            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            admin.setCreatedAt(java.time.LocalDateTime.now());
            userRepository.save(admin);
        }

        if(userRepository.findByEmail("integration_user@example.com").isEmpty()) {
            User user = new User();
            user.setName("User");
            user.setEmail("integration_user@example.com");
            user.setPassword(passwordEncoder.encode("userpass"));
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            user.setCreatedAt(java.time.LocalDateTime.now());
            userRepository.save(user);
        }

        if(vendorRepository.findByVendorName("IntegrationVendor").isEmpty()) {
            vendorRepository.save(new Vendor("IntegrationVendor", "vendor@example.com", "1234567890"));
        }

        if(ruleRepository.findByRuleName("IntegrationRule").isEmpty()) {
            ruleRepository.save(new DepreciationRule("IntegrationRule", "STRAIGHT_LINE", 5, 100.0));
        }
    }
}