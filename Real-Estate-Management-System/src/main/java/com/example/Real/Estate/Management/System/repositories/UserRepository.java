package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
