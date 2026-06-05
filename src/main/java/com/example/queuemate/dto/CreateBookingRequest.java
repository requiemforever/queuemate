package com.example.queuemate.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateBookingRequest {

    @NotBlank(message = "Імʼя студента не може бути порожнім")
    private String studentName;

    @NotBlank(message = "Тема консультації не може бути порожньою")
    private String topic;

    @NotNull(message = "Час консультації обовʼязковий")
    @Future(message = "Час консультації має бути в майбутньому")
    private LocalDateTime requestedTime;

    public String getStudentName() {
        return studentName;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }
}