package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}


//This interface is a Spring Data JPA repository for the User entity. It extends JpaRepository
// and is responsible for performing CRUD operations and querying the User entity in the database.

//important for managing user data, especially for authentication and user management purposes.