package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------- REGISTER ----------
    @Override
    public User registerUser(String email, String password, String name) {

        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email is required");
        }

        if (password == null || password.isBlank()) {
            throw new BadRequestException("Password is required");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    // ---------- LOGIN / AUTH ----------
    @Override
    public User authenticate(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }

    // ---------- QUERIES ----------
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Set<String> getRoleNames(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
