package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seed(
            UserRepository userRepo,
            RoleRepository roleRepo,
            VendorRepository vendorRepo,
            DepreciationRuleRepository ruleRepo,
            BCryptPasswordEncoder enc
    ) {
        return args -> {
            Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> roleRepo.save(new Role("ADMIN")));
            Role userRole  = roleRepo.findByName("USER").orElseGet(() -> roleRepo.save(new Role("USER")));

            if (userRepo.findByEmail("integration_admin@example.com").isEmpty()) {
                userRepo.save(new User("Integration Admin", "integration_admin@example.com", enc.encode("adminpass"), Set.of(adminRole)));
            }

            if (userRepo.findByEmail("integration_user@example.com").isEmpty()) {
                userRepo.save(new User("Integration User", "integration_user@example.com", enc.encode("userpass"), Set.of(userRole)));
            }

            if (vendorRepo.findByVendorName("IntegrationVendor").isEmpty()) {
                vendorRepo.save(new Vendor("IntegrationVendor", "vendor@example.com", "9999999999"));
            }

            if (ruleRepo.findByRuleName("IntegrationRule").isEmpty()) {
                ruleRepo.save(new DepreciationRule("IntegrationRule", "STRAIGHT_LINE", 1, 0.0));
            }
        };
    }
}
