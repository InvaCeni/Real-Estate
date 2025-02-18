package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import com.example.Real.Estate.Management.System.request.AuthRequest;
import com.example.Real.Estate.Management.System.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.Real.Estate.Management.System.request.AuthRequest;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public String login(AuthRequest authRequest) {
        Optional<User> userOpt = userRepository.findByEmail(authRequest.getEmail());
        if (userOpt.isPresent() && passwordEncoder.matches(authRequest.getPassword(), userOpt.get().getPassword())) {
            return jwtUtil.generateToken(authRequest.getEmail(), userOpt.get().getRole().name());
        }
        throw new RuntimeException("Invalid credentials");
    }
}

//This class handles the core business logic for user authentication, including registration and login.
// It interacts with the UserRepository for database operations and uses JWT (JSON Web Tokens) for
// generating tokens after successful authentication. It also uses BCrypt for securely encoding user passwords.