package dev.nyanchuk.airline.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservationDTO = reservationService.getReservationById(id);
        return ResponseEntity.ok().body(reservationDTO);
    }

    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDetails) {
        return reservationService.updateReservation(id, reservationDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}