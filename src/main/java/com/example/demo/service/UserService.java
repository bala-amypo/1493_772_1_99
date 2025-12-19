package com.example.demo.service;
import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
    User registerUser(User user);
    User findByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();

}
