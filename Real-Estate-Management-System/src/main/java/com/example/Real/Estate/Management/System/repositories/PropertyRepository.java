package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByUser(User userId);

    Page<Property> findByNameContainingIgnoreCaseAndFurnishedInAndParkingIn(
            String searchTerm, boolean[] furnishedValues, boolean[] parkingValues, Pageable pageable
    );
}