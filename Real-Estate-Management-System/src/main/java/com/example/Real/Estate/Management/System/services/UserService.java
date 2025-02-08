package com.example.Real.Estate.Management.System.services;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
