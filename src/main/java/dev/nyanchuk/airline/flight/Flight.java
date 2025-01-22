package dev.nyanchuk.airline.flight;

import dev.nyanchuk.airline.airport.Airport;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int availableSeats;
    private boolean status; // true for available, false for not available

    // constructor
    public Flight() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}