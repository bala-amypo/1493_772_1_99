package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataSeeder {

    @Bean
    public CommandLineRunner seed(UserRepository userRepo,
                                 RoleRepository roleRepo,
                                 VendorRepository vendorRepo,
                                 DepreciationRuleRepository ruleRepo,
                                 BCryptPasswordEncoder encoder) {

        return args -> {

            // Seed Roles
            if (roleRepo.findByName("ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                roleRepo.save(adminRole);
            }

            if (roleRepo.findByName("USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("USER");
                roleRepo.save(userRole);
            }

            Role ADMIN = roleRepo.findByName("ADMIN").get();
            Role USER = roleRepo.findByName("USER").get();

            // Seed Users
            if (userRepo.findByEmail("integration_admin@example.com").isEmpty()) {
                User a = new User();
                a.setName("Integration Admin");
                a.setEmail("integration_admin@example.com");
                a.setPassword(encoder.encode("adminpass"));
                a.setCreatedAt(LocalDateTime.now());
                a.setRoles(Set.of(ADMIN));
                userRepo.save(a);
            }

            if (userRepo.findByEmail("integration_user@example.com").isEmpty()) {
                User u = new User();
                u.setName("Integration User");
                u.setEmail("integration_user@example.com");
                u.setPassword(encoder.encode("userpass"));
                u.setCreatedAt(LocalDateTime.now());
                u.setRoles(Set.of(USER));
                userRepo.save(u);
            }

            // Seed Vendor
            if (vendorRepo.findByVendorName("IntegrationVendor").isEmpty()) {
                Vendor v = new Vendor();
                v.setVendorName("IntegrationVendor");
                v.setContactEmail("vendor@example.com");
                v.setPhone("1234567890");
                v.setCreatedAt(LocalDateTime.now());
                vendorRepo.save(v);
            }

            // Seed Depreciation Rule
            if (ruleRepo.findByRuleName("IntegrationRule").isEmpty()) {
                DepreciationRule r = new DepreciationRule();
                r.setRuleName("IntegrationRule");
                r.setMethod("STRAIGHT_LINE");
                r.setUsefulLifeYears(5);
                r.setSalvageValue(0.0);
                r.setCreatedAt(LocalDateTime.now());
                ruleRepo.save(r);
            }

            System.out.println("Seeded Integration Data Successfully ✔");
        };
    }
}
