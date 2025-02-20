package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.enums.Role;
import com.example.Real.Estate.Management.System.utils.JwtUtil;
import com.example.Real.Estate.Management.System.request.LogInResponse;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import org.apache.commons.lang3.RandomStringUtils;



@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtTokenProvider;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtTokenProvider, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void signUpUser(String username, String email, String password, Role role) {

        try {
            String hashedPassword = passwordEncoder.encode(password);

            User user = new User(username, email, hashedPassword, role);

            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error during user registration in service", e);
        }
    }


    public LogInResponse logIn(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {


            String token = jwtTokenProvider.generateToken(user);


            Cookie cookie = new Cookie("access_token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            user.setPassword("");




            return new LogInResponse(true, "success creating cookie",  user , cookie);
        } else {
            return new LogInResponse(false, "Wrong credentials!",  null , null);
        }
    }

    public LogInResponse googleAuth(String email, String username, String avatar) {
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {
            String token = jwtTokenProvider.generateToken(existingUser);


            Cookie cookie = new Cookie("access_token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            existingUser.setPassword(null);


            return new LogInResponse(true, "success creating cookie", existingUser, cookie);
        } else {

            String generatedPassword = RandomStringUtils.random(8, true, true);



            String hashedPassword = passwordEncoder.encode(generatedPassword);

            User newUser = new User(username, email, hashedPassword, avatar);

            userRepository.save(newUser);

            String token = jwtTokenProvider.generateToken(newUser);


            Cookie cookie = new Cookie("access_token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");


            System.out.println("####### user doesn't exist details are: " + newUser.toString());


            newUser.setPassword(null);

            return new LogInResponse(true, "success creating cookie", newUser, cookie);

        }
    }

    public void signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("access_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}