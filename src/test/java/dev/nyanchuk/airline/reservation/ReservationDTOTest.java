package dev.nyanchuk.airline.reservation;

import dev.nyanchuk.airline.flight.Flight;
import dev.nyanchuk.airline.user.User;
import dev.nyanchuk.airline.role.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDTOTest {

    @Test
    public void testDefaultConstructor() {
        ReservationDTO reservation = new ReservationDTO();
        assertNull(reservation.getId());
        assertNull(reservation.getUser());
        assertNull(reservation.getFlight());
        assertNull(reservation.getReservationDate());
        assertNull(reservation.getStatus());
    }

    @Test
    public void testConstructorWithAllFields() {
        User user = new User(1L, "user", "password", Role.USER, "profileImageUrl");
        Flight flight = new Flight(1L, null, null, LocalDateTime.of(2025, 1, 31, 10, 0), LocalDateTime.of(2025, 1, 31, 14, 0), 100, true);
        LocalDateTime reservationDate = LocalDateTime.of(2025, 1, 30, 12, 0);
        String status = "CONFIRMED";

        ReservationDTO reservation = new ReservationDTO(1L, user, flight, reservationDate, status);

        assertEquals(1L, reservation.getId());
        assertEquals(user, reservation.getUser());
        assertEquals(flight, reservation.getFlight());
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals("CONFIRMED", reservation.getStatus());
    }

    @Test
    public void testConstructorForMinimalReservationCreation() {
        ReservationDTO reservation = new ReservationDTO(1L, 1L, "passengerName");

        assertEquals(1L, reservation.getId());
        assertNotNull(reservation.getUser());
        assertEquals("passengerName", reservation.getUser().getUsername());
        assertEquals(Role.USER, reservation.getUser().getRole());
        assertNull(reservation.getUser().getPassword());
        assertNull(reservation.getUser().getProfileImageUrl());
        assertNotNull(reservation.getFlight());
        assertEquals(1L, reservation.getFlight().getId());
        assertNotNull(reservation.getReservationDate());
        assertEquals("PENDING", reservation.getStatus());
    }

    @Test
    public void testSettersAndGetters() {
        ReservationDTO reservation = new ReservationDTO();
        User user = new User(2L, "newUser", "newPassword", Role.ADMIN, "newProfileImageUrl");
        Flight flight = new Flight(2L, null, null, LocalDateTime.of(2025, 2, 1, 10, 0), LocalDateTime.of(2025, 2, 1, 14, 0), 200, false);
        LocalDateTime reservationDate = LocalDateTime.of(2025, 1, 30, 14, 0);
        String status = "CANCELLED";

        reservation.setId(2L);
        reservation.setUser(user);
        reservation.setFlight(flight);
        reservation.setReservationDate(reservationDate);
        reservation.setStatus(status);

        assertEquals(2L, reservation.getId());
        assertEquals(user, reservation.getUser());
        assertEquals(flight, reservation.getFlight());
        assertEquals(reservationDate, reservation.getReservationDate());
        assertEquals("CANCELLED", reservation.getStatus());
    }
}