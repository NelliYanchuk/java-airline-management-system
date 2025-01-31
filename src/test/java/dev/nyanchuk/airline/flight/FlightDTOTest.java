package dev.nyanchuk.airline.flight;

import dev.nyanchuk.airline.airport.Airport;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightDTOTest {

    @Test
    public void testConstructorAndGetters() {
        Airport origin = new Airport(1L, "JFK", "New York");
        Airport destination = new Airport(2L, "LAX", "Los Angeles");
        LocalDateTime departureDate = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2025, 1, 31, 14, 0);
        int availableSeats = 100;
        boolean status = true;

        FlightDTO flight = new FlightDTO(1L, origin, destination, departureDate, arrivalDate, availableSeats, status);

        assertEquals(1L, flight.getId());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(departureDate, flight.getDepartureDate());
        assertEquals(arrivalDate, flight.getArrivalDate());
        assertEquals(100, flight.getAvailableSeats());
        assertTrue(flight.isStatus());
    }

    @Test
    public void testSetters() {
        FlightDTO flight = new FlightDTO(null, null, null, null, null, 0, false);

        Airport origin = new Airport(1L, "JFK", "New York");
        Airport destination = new Airport(2L, "LAX", "Los Angeles");
        LocalDateTime departureDate = LocalDateTime.of(2025, 1, 31, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2025, 1, 31, 14, 0);
        int availableSeats = 100;
        boolean status = true;

        flight.setId(1L);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);
        flight.setArrivalDate(arrivalDate);
        flight.setAvailableSeats(availableSeats);
        flight.setStatus(status);

        assertEquals(1L, flight.getId());
        assertEquals(origin, flight.getOrigin());
        assertEquals(destination, flight.getDestination());
        assertEquals(departureDate, flight.getDepartureDate());
        assertEquals(arrivalDate, flight.getArrivalDate());
        assertEquals(100, flight.getAvailableSeats());
        assertTrue(flight.isStatus());
    }
}