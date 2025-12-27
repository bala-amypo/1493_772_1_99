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
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(
            UserRepository userRepository,
            RoleRepository roleRepository,
            DepreciationRuleRepository ruleRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            // -------- ROLES --------
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role("ADMIN"));
                roleRepository.save(new Role("USER"));
            }

            Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            Role userRole = roleRepository.findByName("USER").orElseThrow();

            // -------- USERS --------
            if (userRepository.count() == 0) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);

                User user = new User();
                user.setName("User");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(Set.of(userRole));
                userRepository.save(user);
            }

            // -------- DEPRECIATION RULES --------
            if (ruleRepository.count() == 0) {

                DepreciationRule r1 = new DepreciationRule();
                r1.setRuleName("Straight Line");
                r1.setMethod("STRAIGHT_LINE");
                r1.setUsefulLifeYears(5);
                r1.setSalvageValue(100);
                r1.setCreatedAt(LocalDateTime.now());
                ruleRepository.save(r1);

                DepreciationRule r2 = new DepreciationRule();
                r2.setRuleName("Declining Balance");
                r2.setMethod("DECLINING_BALANCE");
                r2.setUsefulLifeYears(5);
                r2.setSalvageValue(100);
                r2.setCreatedAt(LocalDateTime.now());
                ruleRepository.save(r2);
            }
        };
    }
}
