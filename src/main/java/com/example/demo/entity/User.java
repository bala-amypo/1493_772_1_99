package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String name;


@Column(unique = true)
private String email;


private String password;


private LocalDateTime createdAt;


@ManyToMany(fetch = FetchType.EAGER)
private Set<Role> roles;


public User() {}


public User(String name, String email, String password, Set<Role> roles) {
this.name = name;
this.email = email;
this.password = password;
this.roles = roles;
this.createdAt = LocalDateTime.now();
}


public Long getId() { return id; }
public String getEmail() { return email; }
public String getPassword() { return password; }
public Set<Role> getRoles() { return roles; }


// User
public void setRoles(Set<Role> roles) {
    this.roles = roles;
}

}