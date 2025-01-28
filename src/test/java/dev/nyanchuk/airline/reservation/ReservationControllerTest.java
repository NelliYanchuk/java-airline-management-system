/* package dev.nyanchuk.airline.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private ReservationDTO reservationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();

        reservationDTO = new ReservationDTO(1L, 1L, "John Doe");
    }

    @Test
    void testDeleteReservation() throws Exception {
        doNothing().when(reservationService).deleteReservation(1L);

        mockMvc.perform(delete("/api/reservations/1"))
                .andExpect(status().isOk());

        verify(reservationService, times(1)).deleteReservation(1L);
    }
} */