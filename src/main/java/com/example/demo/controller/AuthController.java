package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepo;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService, UserRepository userRepo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        try {
            User user = userService.registerUser(body);
            return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "name", user.getName()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            // authenticate user
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );

            // find user in DB
            User user = userRepo.findByEmail(req.getEmail()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
            }

            // collect roles
            Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

            // generate JWT token
            String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);

            // return login success response with token
            return ResponseEntity.ok(Map.of(
                "token", token,
                "id", user.getId(),
                "email", user.getEmail(),
                "roles", roles
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
        }
    }
}
