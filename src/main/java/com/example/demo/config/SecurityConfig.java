package com.example.demo.config;

import com.example.demo.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final JwtAuthenticationEntryPoint entryPoint;

    public SecurityConfig(JwtUtil jwtUtil,
                          JwtAuthenticationEntryPoint entryPoint) {
        this.jwtUtil = jwtUtil;
        this.entryPoint = entryPoint;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .exceptionHandling(ex ->
                ex.authenticationEntryPoint(entryPoint)
            )

            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth
                // PUBLIC ENDPOINTS
                .requestMatchers(
                        "/",
                        "/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/error"
                ).permitAll()

                // API ENDPOINTS (JWT FILTER WILL HANDLE AUTH)
                .requestMatchers("/api/**").permitAll()

                .anyRequest().permitAll()
            );

        // IMPORTANT: JWT FILTER STILL RUNS
        http.addFilterBefore(jwtFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
