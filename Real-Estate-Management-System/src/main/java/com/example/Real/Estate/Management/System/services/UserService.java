package com.example.Real.Estate.Management.System.services;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import com.example.Real.Estate.Management.System.request.UserRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserRequest userRequest){

        if(userRequest == null){
            throw new ServiceException("Null request");
        }
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        user.setPassword((userRequest.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public User updateUser( Long userId, UserRequest userRequest){

        User responseUser = null;
        Optional<User> existingUser = userRepository.findById(userId);

        if(!existingUser.isPresent()){
            throw new ServiceException("useri nuk ekziston");
        }

        responseUser = existingUser.get();

        if(userRequest == null){
            throw new ServiceException("Null request");
        }

        if(userRequest.getName() != null && !userRequest.getName().isEmpty()){
            responseUser.setName(userRequest.getName());
        }
        if(userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()){
            responseUser.setEmail(userRequest.getEmail());
        }
        if(userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()){
            responseUser.setPassword(userRequest.getPassword());
        }
        userRepository.save(responseUser);
        return responseUser;

    }

    public int deleteUser(Long userId){
        Optional<User> existingUser = userRepository.findById(userId);

        if(!existingUser.isPresent()){
            throw new ServiceException("useri nuk ekziston");
        }

        try {
            userRepository.deleteById(userId);
            return 1;
        }
        catch (Exception e){
            return 0;
        }

    }
}


//The UserService class handles the business logic related to the User entity.
// It acts as an intermediary between the controller and the UserRepository,
// providing methods to interact with user data, such as fetching users from
// the database.The UserService class provides a clean and centralized location
// to manage user-related business logic.