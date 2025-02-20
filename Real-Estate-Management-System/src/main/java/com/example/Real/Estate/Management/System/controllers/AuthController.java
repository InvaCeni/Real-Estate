package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.request.*;
import com.example.Real.Estate.Management.System.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignupRequest signupRequest) {
        try {
            authService.signUpUser(signupRequest.getUsername(), signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getRole());
            return ResponseEntity.ok(new ApiResponse(true, null, null , "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, null, null,"Error during user registration"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> logIn(@RequestBody UserSigninRequest request, HttpServletResponse response) {
        LogInResponse logInResponse = authService.logIn(request.getEmail(), request.getPassword());

        if (logInResponse.success()) {
            response.addCookie(logInResponse.cookie());
            return ResponseEntity.ok(new ApiResponse(true, logInResponse.user(), null, "Authentication successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, null, null, logInResponse.message()));
        }
    }

    @PostMapping("/googleAuth")
    public ResponseEntity<ApiResponse> googleAuth(@RequestBody GoogleAuthRequest googleAuthRequest, HttpServletResponse response) {


        try {
            LogInResponse logInResponse = authService.googleAuth(
                    googleAuthRequest.getEmail(),
                    googleAuthRequest.getUsername(),
                    googleAuthRequest.getAvatar()
            );

            if (logInResponse.success()) {
                response.addCookie(logInResponse.cookie());
                return ResponseEntity.ok(new ApiResponse(true, logInResponse.user(), null, "Authentication successful"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, null, null, logInResponse.message()));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/signout")
    public ResponseEntity<ApiResponse> signOut(HttpServletResponse response) {
        try {
            authService.signOut(response);
            return ResponseEntity.ok(new ApiResponse(true, null, null , "Sign-out successful!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error during sign-out"));
        }
    }
}