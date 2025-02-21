package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}