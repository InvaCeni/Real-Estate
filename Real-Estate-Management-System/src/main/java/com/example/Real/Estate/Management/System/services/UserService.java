package com.example.Real.Estate.Management.System.services;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Real.Estate.Management.System.request.ApiResponse;
import com.example.Real.Estate.Management.System.request.UserUpdateRequest;
import com.example.Real.Estate.Management.System.models.Property;

import com.example.Real.Estate.Management.System.repositories.PropertyRepository;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository PropertyRepository;

    public User updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Property> getUserProperty(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return PropertyRepository.findByUser(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }



}