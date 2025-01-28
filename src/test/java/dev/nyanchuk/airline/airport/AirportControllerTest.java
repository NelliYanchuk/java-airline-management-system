package dev.nyanchuk.airline.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AirportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    private Airport airport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();

        airport = new Airport();
        airport.setId(1L);
        airport.setName("JFK International Airport");
        airport.setLocation("New York, NY");
    }

    @Test
    void testCreateAirport() throws Exception {
        when(airportService.createAirport(any(Airport.class))).thenReturn(airport);

        mockMvc.perform(post("/api/airports")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JFK International Airport\",\"location\":\"New York, NY\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("JFK International Airport"))
                .andExpect(jsonPath("$.location").value("New York, NY"));

        verify(airportService, times(1)).createAirport(any(Airport.class));
    }

    @Test
    void testGetAllAirports() throws Exception {
        List<Airport> airports = Arrays.asList(airport);
        when(airportService.getAllAirports()).thenReturn(airports);

        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JFK International Airport"))
                .andExpect(jsonPath("$[0].location").value("New York, NY"));

        verify(airportService, times(1)).getAllAirports();
    }

    @Test
    void testGetAirportById() throws Exception {
        when(airportService.getAirportById(1L)).thenReturn(airport);

        mockMvc.perform(get("/api/airports/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("JFK International Airport"))
                .andExpect(jsonPath("$.location").value("New York, NY"));

        verify(airportService, times(1)).getAirportById(1L);
    }

    @Test
    void testUpdateAirport() throws Exception {
        when(airportService.updateAirport(eq(1L), any(Airport.class))).thenReturn(airport);

        mockMvc.perform(put("/api/airports/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JFK International Airport\",\"location\":\"New York, NY\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("JFK International Airport"))
                .andExpect(jsonPath("$.location").value("New York, NY"));

        verify(airportService, times(1)).updateAirport(eq(1L), any(Airport.class));
    }

    @Test
    void testDeleteAirport() throws Exception {
        doNothing().when(airportService).deleteAirport(1L);

        mockMvc.perform(delete("/api/airports/1"))
                .andExpect(status().isOk());

        verify(airportService, times(1)).deleteAirport(1L);
    }
}