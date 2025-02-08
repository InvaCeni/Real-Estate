package com.example.Real.Estate.Management.System.repositories;
import com.example.Real.Estate.Management.System.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocation(String location);
}
