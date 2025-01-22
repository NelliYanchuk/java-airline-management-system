package dev.nyanchuk.airline.flight;

import dev.nyanchuk.airline.airport.Airport;
import dev.nyanchuk.airline.flight.exception.FlightNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;
    private FlightDTO flightDTO;
    private Airport originAirport;
    private Airport destinationAirport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        originAirport = new Airport();
        originAirport.setId(1L);
        originAirport.setName("John F. Kennedy International Airport");
        originAirport.setLocation("New York, USA");

        destinationAirport = new Airport();
        destinationAirport.setId(2L);
        destinationAirport.setName("Los Angeles International Airport");
        destinationAirport.setLocation("Los Angeles, USA");

        flight = new Flight();
        flight.setId(1L);
        flight.setOrigin(originAirport);
        flight.setDestination(destinationAirport);
        flight.setDepartureDate(LocalDateTime.now());
        flight.setArrivalDate(LocalDateTime.now().plusHours(5));
        flight.setAvailableSeats(100);
        flight.setStatus(true);

        flightDTO = new FlightDTO(1L, originAirport, destinationAirport,
                LocalDateTime.now(), LocalDateTime.now().plusHours(5), 100, true);
    }

    @Test
    void testCreateFlight() {
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        FlightDTO createdFlight = flightService.createFlight(flightDTO);

        assertEquals(flightDTO.getOrigin().getId(), createdFlight.getOrigin().getId());
        assertEquals(flightDTO.getDestination().getId(), createdFlight.getDestination().getId());
        assertEquals(flightDTO.getAvailableSeats(), createdFlight.getAvailableSeats());
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testGetFlightById() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        FlightDTO retrievedFlight = flightService.getFlightById(1L);

        assertEquals(flightDTO.getOrigin().getId(), retrievedFlight.getOrigin().getId());
        assertEquals(flightDTO.getDestination().getId(), retrievedFlight.getDestination().getId());
        assertEquals(flightDTO.getAvailableSeats(), retrievedFlight.getAvailableSeats());
    }

    @Test
    void testUpdateFlight() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        FlightDTO updatedFlight = flightService.updateFlight(1L, flightDTO);

        assertEquals(flightDTO.getOrigin().getId(), updatedFlight.getOrigin().getId());
        assertEquals(flightDTO.getDestination().getId(), updatedFlight.getDestination().getId());
        assertEquals(flightDTO.getAvailableSeats(), updatedFlight.getAvailableSeats());
        verify(flightRepository, times(1)).findById(1L);
        verify(flightRepository, times(1)).save(any(Flight.class));
    }

    @Test
    void testDeleteFlight() {
        doNothing().when(flightRepository).deleteById(1L);

        flightService.deleteFlight(1L);

        verify(flightRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetFlightById_NotFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FlightNotFoundException.class, () -> flightService.getFlightById(1L));
    }
}