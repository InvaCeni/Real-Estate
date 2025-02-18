package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.models.Booking;
import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.repositories.BookingRepository;
import com.example.Real.Estate.Management.System.repositories.PropertyRepository;
import com.example.Real.Estate.Management.System.repositories.UserRepository;
import com.example.Real.Estate.Management.System.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingRequest bookingRequest) {
        Property property = propertyRepository.findById(bookingRequest.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        User customer = userRepository.findById(bookingRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        if (customer.getRole() != User.Role.CUSTOMER) {
            throw new RuntimeException("User is not a customer and cannot make a booking");
        }

        if (!property.getIsAvailable()) {
            throw new RuntimeException("Property is not available");
        }

        LocalDateTime bookingDateTime = bookingRequest.getBookingDate().atStartOfDay();

        Booking booking = new Booking(property, customer, bookingDateTime);

        return bookingRepository.save(booking);
    }

    public Booking confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CANCELED");
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
}