package dev.nyanchuk.airline.reservation;

import dev.nyanchuk.airline.flight.Flight;
import dev.nyanchuk.airline.user.User;
import dev.nyanchuk.airline.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ReservationController reservationController;

    private ReservationDTO reservation1;
    private ReservationDTO reservation2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        User user1 = new User(1L, "user1", "password1", Role.USER, null);
        User user2 = new User(2L, "user2", "password2", Role.USER, null);
        Flight flight = new Flight(1L, null, null, LocalDateTime.of(2025, 1, 31, 10, 0), LocalDateTime.of(2025, 1, 31, 14, 0), 100, true);

        reservation1 = new ReservationDTO(1L, user1, flight, LocalDateTime.of(2025, 1, 30, 12, 0), "CONFIRMED");
        reservation2 = new ReservationDTO(2L, user2, flight, LocalDateTime.of(2025, 1, 30, 12, 0), "PENDING");
    }

    @Test
    public void testCreateReservation() throws Exception {
        doReturn(reservation1).when(reservationService).createReservation(any(ReservationDTO.class));

        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"user\":{\"id\":1,\"username\":\"user1\",\"password\":\"password1\",\"role\":\"USER\"},\"flight\":{\"id\":1,\"departureDate\":\"2025-01-31T10:00:00\",\"arrivalDate\":\"2025-01-31T14:00:00\",\"availableSeats\":100,\"status\":true},\"reservationDate\":\"2025-01-30T12:00:00\",\"status\":\"CONFIRMED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user.username").value("user1"))
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }

    @Test
    public void testCreateReservation2() throws Exception {
        doReturn(reservation2).when(reservationService).createReservation(any(ReservationDTO.class));

        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"user\":{\"id\":2,\"username\":\"user2\",\"password\":\"password2\",\"role\":\"USER\"},\"flight\":{\"id\":1,\"departureDate\":\"2025-01-31T10:00:00\",\"arrivalDate\":\"2025-01-31T14:00:00\",\"availableSeats\":100,\"status\":true},\"reservationDate\":\"2025-01-30T12:00:00\",\"status\":\"PENDING\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.user.username").value("user2"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testDeleteReservation() throws Exception {
        mockMvc.perform(delete("/api/reservations/1"))
                .andExpect(status().isOk());
    }
}