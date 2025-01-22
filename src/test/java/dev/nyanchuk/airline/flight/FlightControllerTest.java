package dev.nyanchuk.airline.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import dev.nyanchuk.airline.airport.Airport;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    private FlightDTO flightDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();

        flightDTO = new FlightDTO(1L, new Airport(1L, "JFK", "New York, USA"), 
                                  new Airport(2L, "LAX", "Los Angeles, USA"), 
                                  LocalDateTime.now(), LocalDateTime.now().plusHours(5), 100, true);
    }

    @Test
    void testCreateFlight() throws Exception {
        when(flightService.createFlight(any(FlightDTO.class))).thenReturn(flightDTO);

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"origin\":{\"id\":1,\"name\":\"JFK\",\"location\":\"New York, USA\"},\"destination\":{\"id\":2,\"name\":\"LAX\",\"location\":\"Los Angeles, USA\"},\"departureDate\":\"2025-01-22T18:27:14\",\"arrivalDate\":\"2025-01-22T23:27:14\",\"availableSeats\":100,\"status\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin.name").value("JFK"))
                .andExpect(jsonPath("$.destination.name").value("LAX"))
                .andExpect(jsonPath("$.availableSeats").value(100));

        verify(flightService, times(1)).createFlight(any(FlightDTO.class));
    }

    @Test
    void testGetAllFlights() throws Exception {
        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flightDTO));

        mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].origin.name").value("JFK"))
                .andExpect(jsonPath("$[0].destination.name").value("LAX"));

        verify(flightService, times(1)).getAllFlights();
    }

    @Test
    void testGetFlightById() throws Exception {
        when(flightService.getFlightById(1L)).thenReturn(flightDTO);

        mockMvc.perform(get("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin.name").value("JFK"))
                .andExpect(jsonPath("$.destination.name").value("LAX"));

        verify(flightService, times(1)).getFlightById(1L);
    }

    @Test
    void testUpdateFlight() throws Exception {
        when(flightService.updateFlight(eq(1L), any(FlightDTO.class))).thenReturn(flightDTO);

        mockMvc.perform(put("/api/flights/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"origin\":{\"id\":1,\"name\":\"JFK\",\"location\":\"New York, USA\"},\"destination\":{\"id\":2,\"name\":\"LAX\",\"location\":\"Los Angeles, USA\"},\"departureDate\":\"2025-01-22T18:27:14\",\"arrivalDate\":\"2025-01-22T23:27:14\",\"availableSeats\":100,\"status\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origin.name").value("JFK"))
                .andExpect(jsonPath("$.destination.name").value("LAX"));

        verify(flightService, times(1)).updateFlight(eq(1L), any(FlightDTO.class));
    }

    @Test
    void testDeleteFlight() throws Exception {
        doNothing().when(flightService).deleteFlight(1L);

        mockMvc.perform(delete("/api/flights/1"))
                .andExpect(status().isOk());

        verify(flightService, times(1)).deleteFlight(1L);
    }
}