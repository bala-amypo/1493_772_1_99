package com.example.demo.dto;

import java.util.Collection;

public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Collection<String> roles;

    public AuthResponse(String token, Long userId, String email, Collection<String> roles) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }
    
    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public Collection<String> getRoles() { return roles; }
}