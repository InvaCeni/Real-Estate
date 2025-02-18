package com.example.Real.Estate.Management.System.repositories;
import com.example.Real.Estate.Management.System.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocation(String location);

    @Query("SELECT p FROM Property p WHERE " +
            "(:location IS NULL OR p.location LIKE %:location%) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:propertyType IS NULL OR p.propertyType = :propertyType) AND " +
            "(:isAvailable IS NULL OR p.isAvailable = :isAvailable)")
    List<Property> searchProperties(
            @Param("location") String location,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("propertyType") String propertyType,
            @Param("isAvailable") Boolean isAvailable
    );
}





