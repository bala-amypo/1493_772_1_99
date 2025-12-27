package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // ✅ FIXED

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // -------- REGISTER --------
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        user.setRoles(Set.of(role));

        User saved = userRepository.save(user);

        Set<String> roles = saved.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(
                saved.getEmail(),
                saved.getId(),
                roles
        );

        return new AuthResponse(token, saved.getId(), saved.getEmail(), roles);
    }

    // -------- LOGIN --------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getId(),
                roles
        );

        return new AuthResponse(token, user.getId(), user.getEmail(), roles);
    }
}
