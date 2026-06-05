package com.example.queuemate.service;

import com.example.queuemate.dto.CreateBookingRequest;
import com.example.queuemate.model.Booking;
import com.example.queuemate.model.BookingStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookingService {

    private final ConcurrentHashMap<UUID, Booking> bookings = new ConcurrentHashMap<>();

    public Booking create(CreateBookingRequest request) {
        Booking booking = new Booking(
                UUID.randomUUID(),
                request.getStudentName(),
                request.getTopic(),
                request.getRequestedTime()
        );

        bookings.put(booking.getId(), booking);

        return booking;
    }

    public List<Booking> findAll(BookingStatus status) {
        List<Booking> result = new ArrayList<>();

        for (Booking booking : bookings.values()) {
            if (status == null || booking.getStatus() == status) {
                result.add(booking);
            }
        }

        return result;
    }

    public Booking cancel(UUID id) {
        Booking booking = bookings.get(id);

        if (booking == null) {
            throw new RuntimeException("Бронювання не знайдено");
        }

        booking.cancel();

        return booking;
    }
}