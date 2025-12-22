package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Set;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            RoleRepository roleRepo,
            UserRepository userRepo) {

        return args -> {

            // ROLE
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN")));

            // USER
            if (userRepo.count() == 0) {
                User admin = new User(
                        "Admin",
                        "admin@gmail.com",
                        "admin123",
                        Set.of(adminRole)
                );
                userRepo.save(admin);
            }
        };
    }
}
