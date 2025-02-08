package com.example.Real.Estate.Management.System.models;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;  // 'admin', 'agent', or 'customer'

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;  // Return the email field
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;  // Return the password field
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;  // Return the role field
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        admin, agent, customer
    }
}



