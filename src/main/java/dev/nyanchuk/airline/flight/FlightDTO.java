package dev.nyanchuk.airline.flight;

import java.time.LocalDateTime;

public class FlightDTO {
    private Long id;
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private int availableSeats;
    private boolean status;

    // Constructors
    public FlightDTO() {}

    public FlightDTO(Long id, String origin, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate, int availableSeats, boolean status) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.availableSeats = availableSeats;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
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