package dev.nyanchuk.airline.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.nyanchuk.airline.reservation.exception.ReservationNotFoundException;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = toEntity(reservationDTO);
        return toDTO(reservationRepository.save(reservation));
    }

    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        return toDTO(reservation);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservation.setUser(reservationDetails.getUser());
        reservation.setFlight(reservationDetails.getFlight());
        reservation.setReservationDate(reservationDetails.getReservationDate());
        reservation.setStatus(reservationDetails.getStatus());
        return toDTO(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO toDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getUser(), reservation.getFlight(),
                reservation.getReservationDate(), reservation.getStatus());
    }

    private Reservation toEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setUser(reservationDTO.getUser());
        reservation.setFlight(reservationDTO.getFlight());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setStatus(reservationDTO.getStatus());
        return reservation;
    }
}