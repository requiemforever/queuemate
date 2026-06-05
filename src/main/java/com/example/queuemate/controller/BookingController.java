package com.example.queuemate.controller;

import com.example.queuemate.dto.BookingsResponse;
import com.example.queuemate.dto.CreateBookingRequest;
import com.example.queuemate.model.Booking;
import com.example.queuemate.model.BookingStatus;
import com.example.queuemate.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking create(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.create(request);
    }

    @GetMapping
    public BookingsResponse findAll(@RequestParam(required = false) BookingStatus status) {
        return new BookingsResponse(bookingService.findAll(status));
    }

    @PatchMapping("/{id}/cancel")
    public Booking cancel(@PathVariable UUID id) {
        return bookingService.cancel(id);
    }
}