package dev.nyanchuk.airline.reservation;

import dev.nyanchuk.airline.flight.Flight;
import dev.nyanchuk.airline.user.User;
import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private User user;
    private Flight flight;
    private LocalDateTime reservationDate;
    private String status;

    // Default constructor
    public ReservationDTO() {
    }

    // Constructors, getters, and setters
    public ReservationDTO(Long id, User user, Flight flight, LocalDateTime reservationDate, String status) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public ReservationDTO(Long id, Long flightId, String passengerName) {
        this.id = id;
        this.user = new User(id, passengerName, null, null);
        this.flight = new Flight(flightId, null, null, null, null, 0, false);
        this.reservationDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}