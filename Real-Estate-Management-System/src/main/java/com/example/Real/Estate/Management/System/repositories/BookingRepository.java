package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

        List<Booking> findByPropertyId(Long propertyId);
        List<Booking> findByCustomerId(Long customerId);

}

