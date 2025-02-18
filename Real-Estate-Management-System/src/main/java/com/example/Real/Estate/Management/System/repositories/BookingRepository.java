package com.example.Real.Estate.Management.System.repositories;

import com.example.Real.Estate.Management.System.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

        List<Booking> findByPropertyId(Long propertyId);
        List<Booking> findByCustomerId(Long customerId);

}

//This interface is a Spring Data JPA repository for the Booking entity.
// It extends the JpaRepository interface, which provides built-in methods
// for CRUD (Create, Read, Update, Delete) operations and querying the Booking
// entity in the database.

//By extending JpaRepository, BookingRepository inherits various methods such as:
//
//save(): To save or update a Booking.
//findById(): To find a Booking by its id.
//findAll(): To find all Booking entries in the database.
//delete(): To delete a Booking.
//count(): To get the number of Booking records.