package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Role;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
