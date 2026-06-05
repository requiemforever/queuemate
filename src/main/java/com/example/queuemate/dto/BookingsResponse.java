package com.example.queuemate.dto;

import com.example.queuemate.model.Booking;

import java.util.List;

public class BookingsResponse {

    private List<Booking> bookings;
    private int totalCount;

    public BookingsResponse(List<Booking> bookings) {
        this.bookings = bookings;
        this.totalCount = bookings.size();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getTotalCount() {
        return totalCount;
    }
}