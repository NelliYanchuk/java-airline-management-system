package dev.nyanchuk.airline.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
