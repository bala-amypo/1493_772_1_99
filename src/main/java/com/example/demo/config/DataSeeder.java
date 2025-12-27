package com.example.demo.config;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(UserRepository userRepository,
                           RoleRepository roleRepository,
                           DepreciationRuleRepository depreciationRuleRepository,
                           PasswordEncoder passwordEncoder) {
        return args -> {

            // ----- ROLES -----
            if (roleRepository.count() == 0) {
                Role adminRole = new Role("ADMIN");
                Role userRole = new Role("USER");
                roleRepository.save(adminRole);
                roleRepository.save(userRole);
            }

            // ----- USERS -----
            if (userRepository.count() == 0) {
                Role adminRole = roleRepository.findByName("ADMIN");
                Role userRole = roleRepository.findByName("USER");

                User admin = new User();
                admin.setName("admin");               // Changed from setUsername()
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                admin.setRoles(adminRoles);
                userRepository.save(admin);

                User normalUser = new User();
                normalUser.setName("user");           // Changed from setUsername()
                normalUser.setEmail("user@example.com");
                normalUser.setPassword(passwordEncoder.encode("user123"));
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(userRole);
                normalUser.setRoles(userRoles);
                userRepository.save(normalUser);
            }

            // ----- DEPRECIATION RULES -----
            if (depreciationRuleRepository.count() == 0) {
                DepreciationRule rule1 = new DepreciationRule();
                rule1.setRuleName("Straight Line");
                rule1.setMethod("SL");
                rule1.setUsefulLifeYears(5);
                rule1.setSalvageValue(1000);
                rule1.setCreatedAt(LocalDateTime.now());
                depreciationRuleRepository.save(rule1);

                DepreciationRule rule2 = new DepreciationRule();
                rule2.setRuleName("Declining Balance");
                rule2.setMethod("DB");
                rule2.setUsefulLifeYears(5);
                rule2.setSalvageValue(1000);
                rule2.setCreatedAt(LocalDateTime.now());
                depreciationRuleRepository.save(rule2);
            }

            System.out.println("Seeding complete.");
        };
    }
}
