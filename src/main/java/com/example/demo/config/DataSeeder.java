@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(UserRepository userRepository,
                           RoleRepository roleRepository,
                           DepreciationRuleRepository ruleRepository,
                           PasswordEncoder passwordEncoder) {

        return args -> {

            // ---------- ROLES ----------
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

            Role userRole = roleRepository.findByName("USER")
                    .orElseGet(() -> roleRepository.save(new Role("USER")));

            // ---------- ADMIN USER ----------
            userRepository.findByEmail("integration_admin@example.com")
                    .orElseGet(() -> {
                        User admin = new User();
                        admin.setName("IntegrationAdmin");
                        admin.setEmail("integration_admin@example.com");
                        admin.setPassword(passwordEncoder.encode("adminpass"));
                        admin.getRoles().add(adminRole);
                        return userRepository.save(admin);
                    });

            // ---------- NORMAL USER ----------
            userRepository.findByEmail("integration_user@example.com")
                    .orElseGet(() -> {
                        User user = new User();
                        user.setName("IntegrationUser");
                        user.setEmail("integration_user@example.com");
                        user.setPassword(passwordEncoder.encode("userpass"));
                        user.getRoles().add(userRole);
                        return userRepository.save(user);
                    });

            // ---------- DEPRECIATION RULE ----------
            ruleRepository.findByRuleName("IntegrationRule")
                    .orElseGet(() -> {
                        DepreciationRule rule = new DepreciationRule();
                        rule.setRuleName("IntegrationRule");
                        rule.setMethod("STRAIGHT_LINE");
                        rule.setUsefulLifeYears(5);
                        rule.setSalvageValue(10.0);
                        rule.setCreatedAt(LocalDateTime.now());
                        return ruleRepository.save(rule);
                    });

            System.out.println("Data seeding completed.");
        };
    }
}
