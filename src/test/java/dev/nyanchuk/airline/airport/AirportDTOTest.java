package dev.nyanchuk.airline.airport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirportDTOTest {

    @Test
    public void testDefaultConstructor() {
        AirportDTO airport = new AirportDTO();
        assertNull(airport.getId());
        assertNull(airport.getName());
        assertNull(airport.getLocation());
    }

    @Test
    public void testParameterizedConstructor() {
        AirportDTO airport = new AirportDTO(1L, "JFK", "New York");
        assertEquals(1L, airport.getId());
        assertEquals("JFK", airport.getName());
        assertEquals("New York", airport.getLocation());
    }

    @Test
    public void testSettersAndGetters() {
        AirportDTO airport = new AirportDTO();
        airport.setId(2L);
        airport.setName("LAX");
        airport.setLocation("Los Angeles");

        assertEquals(2L, airport.getId());
        assertEquals("LAX", airport.getName());
        assertEquals("Los Angeles", airport.getLocation());
    }
}