package dev.nyanchuk.airline.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations(Authentication authentication) {
        return reservationService.getAllReservations(authentication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id, Authentication authentication) {
        ReservationDTO reservationDTO = reservationService.getReservationById(id, authentication);
        return ResponseEntity.ok().body(reservationDTO);
    }

    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDetails, Authentication authentication) {
        return reservationService.updateReservation(id, reservationDetails, authentication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id, Authentication authentication) {
        reservationService.deleteReservation(id, authentication);
        return ResponseEntity.ok().build();
    }
}