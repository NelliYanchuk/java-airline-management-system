package dev.nyanchuk.airline.reservation;

import dev.nyanchuk.airline.flight.Flight;
import dev.nyanchuk.airline.user.User;
import dev.nyanchuk.airline.airport.Airport;
import dev.nyanchuk.airline.reservation.exception.ReservationNotFoundException;
import dev.nyanchuk.airline.role.Role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private Authentication authentication;

    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private User user;
    private Flight flight;
    private Airport originAirport;
    private Airport destinationAirport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

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

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setFlight(flight);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setStatus("CONFIRMED");

        reservationDTO = new ReservationDTO(1L, user, flight, LocalDateTime.now(), "CONFIRMED");

        // Mock the authentication object
        when(authentication.getName()).thenReturn("testuser");
    }

    @Test
    void testCreateReservation() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);

        assertEquals(reservationDTO.getUser().getId(), createdReservation.getUser().getId());
        assertEquals(reservationDTO.getFlight().getId(), createdReservation.getFlight().getId());
        assertEquals(reservationDTO.getStatus(), createdReservation.getStatus());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testGetReservationById() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationDTO retrievedReservation = reservationService.getReservationById(1L, authentication);

        assertEquals(reservationDTO.getUser().getId(), retrievedReservation.getUser().getId());
        assertEquals(reservationDTO.getFlight().getId(), retrievedReservation.getFlight().getId());
        assertEquals(reservationDTO.getStatus(), retrievedReservation.getStatus());
    }

    @Test
    void testUpdateReservation() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDTO updatedReservation = reservationService.updateReservation(1L, reservationDTO, authentication);

        assertEquals(reservationDTO.getUser().getId(), updatedReservation.getUser().getId());
        assertEquals(reservationDTO.getFlight().getId(), updatedReservation.getFlight().getId());
        assertEquals(reservationDTO.getStatus(), updatedReservation.getStatus());
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testDeleteReservation() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        doNothing().when(reservationRepository).deleteById(1L);

        reservationService.deleteReservation(1L, authentication);

        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetReservationById_NotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ReservationNotFoundException.class, () -> reservationService.getReservationById(1L, authentication));
    }
}