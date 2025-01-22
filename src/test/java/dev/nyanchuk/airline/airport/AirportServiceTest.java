package dev.nyanchuk.airline.airport;

import dev.nyanchuk.airline.airport.exception.AirportNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        airport = new Airport();
        airport.setId(1L);
        airport.setName("John F. Kennedy International Airport");
        airport.setLocation("New York, USA");
    }

    @Test
    void testCreateAirport() {
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport createdAirport = airportService.createAirport(airport);

        assertEquals(airport.getName(), createdAirport.getName());
        assertEquals(airport.getLocation(), createdAirport.getLocation());
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testGetAllAirports() {
        when(airportRepository.findAll()).thenReturn(List.of(airport));

        List<Airport> airports = airportService.getAllAirports();

        assertEquals(1, airports.size());
        assertEquals(airport.getName(), airports.get(0).getName());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void testGetAirportById() {
        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        Airport retrievedAirport = airportService.getAirportById(1L);

        assertEquals(airport.getName(), retrievedAirport.getName());
        verify(airportRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAirport() {
        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport updatedAirport = airportService.updateAirport(1L, airport);

        assertEquals(airport.getName(), updatedAirport.getName());
        assertEquals(airport.getLocation(), updatedAirport.getLocation());
        verify(airportRepository, times(1)).findById(1L);
        verify(airportRepository, times(1)).save(any(Airport.class));
    }

    @Test
    void testDeleteAirport() {
        doNothing().when(airportRepository).deleteById(1L);

        airportService.deleteAirport(1L);

        verify(airportRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAirportById_NotFound() {
        when(airportRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(AirportNotFoundException.class, () -> airportService.getAirportById(1L));
    }
}