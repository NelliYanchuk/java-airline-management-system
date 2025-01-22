package dev.nyanchuk.airline.flight.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(Long id) {
        super("Flight not found with id: " + id);
    }
}