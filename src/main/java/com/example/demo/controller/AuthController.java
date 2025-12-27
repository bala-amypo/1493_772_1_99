package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ---------- REGISTER ----------
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {

        try {
            User saved = userService.registerUser(
                    request.getEmail(),
                    request.getPassword(),
                    request.getName()
            );

            Set<String> roles = userService.getRoleNames(saved);

            String token = jwtUtil.generateToken(
                    saved.getEmail(),
                    saved.getId(),
                    roles
            );

            return new AuthResponse(token, saved.getId(),
                    saved.getEmail(), roles);

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ex.getMessage()
            );
        }
    }

    // ---------- LOGIN ----------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        try {
            User user = userService.authenticate(
                    request.getEmail(),
                    request.getPassword()
            );

            Set<String> roles = userService.getRoleNames(user);

            String token = jwtUtil.generateToken(
                    user.getEmail(),
                    user.getId(),
                    roles
            );

            return new AuthResponse(token, user.getId(),
                    user.getEmail(), roles);

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid credentials"
            );
        }
    }
}
