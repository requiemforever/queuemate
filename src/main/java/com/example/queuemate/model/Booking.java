package com.example.queuemate.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private UUID id;
    private String studentName;
    private String topic;
    private LocalDateTime requestedTime;
    private BookingStatus status;
    private LocalDateTime createdAt;

    public Booking(UUID id, String studentName, String topic, LocalDateTime requestedTime) {
        this.id = id;
        this.studentName = studentName;
        this.topic = topic;
        this.requestedTime = requestedTime;
        this.status = BookingStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}