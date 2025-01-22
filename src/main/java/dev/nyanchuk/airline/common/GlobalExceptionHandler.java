package dev.nyanchuk.airline.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import dev.nyanchuk.airline.user.exception.*;
import dev.nyanchuk.airline.flight.exception.*;
import dev.nyanchuk.airline.reservation.exception.*;
import dev.nyanchuk.airline.airport.exception.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "/api/users");
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFlightNotFoundException(FlightNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "/api/flights");
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "/api/reservations");
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAirportNotFoundException(AirportNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, "/api/airports");
    }

    // Generic handler for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "/api");
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(Exception ex, HttpStatus status, String path) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("path", path);
        
        return new ResponseEntity<>(errorResponse, status);
    }
}