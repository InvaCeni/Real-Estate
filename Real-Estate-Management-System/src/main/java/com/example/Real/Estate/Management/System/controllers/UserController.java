package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.request.UserRequest;
import com.example.Real.Estate.Management.System.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/update/{userId}")
    public User updateUser(@PathParam (value = "userId") Long userId, @Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/delete/{userId}")
    public int deleteUser(@PathParam (value = "userId") Long userId) {
        return userService.deleteUser(userId);
    }
}

//This class is a REST controller responsible for managing user-related actions,
// such as retrieving a list of users. It interacts with the UserService to handle
// the core business logic related to users.mainly focused on reading user data, such as retrieving a list of users.