package com.example.Real.Estate.Management.System.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}

enum BookingStatus {
    PENDING, CONFIRMED, DECLINED
}


//This class represents a booking in the
// Real Estate Management System. It models a
// customer requesting to visit or book a property
// and stores the details related to that booking,
// including the property, customer, booking time, and status.