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
