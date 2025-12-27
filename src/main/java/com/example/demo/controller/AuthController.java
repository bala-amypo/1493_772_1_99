package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository uRepo;
    private final RoleRepository roleRepo;
    private final JwtUtil jwt;
    private final BCryptPasswordEncoder enc;

    public AuthController(UserRepository uRepo, RoleRepository roleRepo, JwtUtil jwt, BCryptPasswordEncoder enc) {
        this.uRepo = uRepo;
        this.roleRepo = roleRepo;
        this.jwt = jwt;
        this.enc = enc;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {

        // Validate unique email
        if (uRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Hash password
        user.setPassword(enc.encode(user.getPassword()));

        // Ensure USER role exists
        Role userRole = roleRepo.findByName("USER")
                .orElseGet(() -> roleRepo.save(new Role("USER")));

        // Assign USER role by default
        user.getRoles().add(userRole);

        // Save user
        User savedUser = uRepo.save(user);

        // Prepare roles set for token
        Set<String> roleNames = savedUser.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        // Generate JWT token
        String token = jwt.generateToken(savedUser.getEmail(), savedUser.getId(), roleNames);

        return new AuthResponse(token, savedUser.getId(), savedUser.getEmail(), roleNames);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Load user
        User user = uRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate password
        if (!enc.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Extract roles
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        // Generate token
        String token = jwt.generateToken(user.getEmail(), user.getId(), roleNames);

        return new AuthResponse(token, user.getId(), user.getEmail(), roleNames);
    }
}
