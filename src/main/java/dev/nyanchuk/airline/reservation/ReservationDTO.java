package dev.nyanchuk.airline.reservation;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long flightId;
    private LocalDateTime reservationDate;
    private String status;

    // Constructors
    public ReservationDTO() {}

    public ReservationDTO(Long id, Long userId, Long flightId, LocalDateTime reservationDate, String status) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
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