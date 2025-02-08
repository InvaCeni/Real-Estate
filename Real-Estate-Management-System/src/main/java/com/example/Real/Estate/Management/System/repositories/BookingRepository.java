package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> { }

