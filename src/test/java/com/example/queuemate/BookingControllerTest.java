package com.example.queuemate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateBooking() throws Exception {
        String json = """
                {
                  "studentName": "Andrii",
                  "topic": "Spring Boot question",
                  "requestedTime": "%s"
                }
                """.formatted(LocalDateTime.now().plusDays(1));

        mockMvc.perform(post("/api/v1/bookings")
                        .header("X-API-Key", "secret-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldRejectPastTime() throws Exception {
        String json = """
                {
                  "studentName": "Andrii",
                  "topic": "Spring Boot question",
                  "requestedTime": "%s"
                }
                """.formatted(LocalDateTime.now().minusDays(1));

        mockMvc.perform(post("/api/v1/bookings")
                        .header("X-API-Key", "secret-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnauthorizedWithoutApiKey() throws Exception {
        mockMvc.perform(get("/api/v1/bookings"))
                .andExpect(status().isUnauthorized());
    }
}