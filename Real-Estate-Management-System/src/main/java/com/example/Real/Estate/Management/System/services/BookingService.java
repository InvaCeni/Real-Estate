package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.models.Booking;
import com.example.Real.Estate.Management.System.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}

//This class is a service layer component that acts as an intermediary between
// the controller and repository layers in your Spring Boot application.
// The BookingService handles the business logic related to Booking entities and
// uses the BookingRepository to interact with the database.