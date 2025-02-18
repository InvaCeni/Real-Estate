package com.example.Real.Estate.Management.System.models;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
@Getter
@Setter
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Role getRole() {
        return role;  
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public enum Role {
        ADMIN, AGENT, CUSTOMER
    }

}



//This class represents a user in the Real Estate Management System, which can be an ADMIN, AGENT, or CUSTOMER.
// It includes essential user attributes such as name, email, password, and role, as well as methods for setting
// and getting these attributes. The password is stored securely using BCryptPasswordEncoder.