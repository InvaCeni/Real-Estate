package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.request.AuthRequest;
import com.example.Real.Estate.Management.System.services.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}

//This class is a REST controller for handling user authentication requests (such as registration and login).
// It interacts with the AuthService to perform the actual business logic