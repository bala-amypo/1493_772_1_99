package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Set;

public interface UserService {

    // ---------- AUTH ----------
    User registerUser(String email, String password, String name);

    User authenticate(String email, String password);

    // ---------- QUERIES ----------
    User findByEmail(String email);

    User findById(Long id);

    Set<String> getRoleNames(User user);
}
