package dev.nyanchuk.airline.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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

    public List<ReservationDTO> getAllReservations(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return reservationRepository.findAll().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        } else {
            String username = authentication.getName();
            return reservationRepository.findByUserUsername(username).stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }
    }

    public ReservationDTO getReservationById(Long id, Authentication authentication) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
            reservation.getUser().getUsername().equals(authentication.getName())) {
            return toDTO(reservation);
        } else {
            throw new AccessDeniedException("You do not have permission to access this reservation.");
        }
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDetails, Authentication authentication) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
            reservation.getUser().getUsername().equals(authentication.getName())) {
            reservation.setUser(reservationDetails.getUser());
            reservation.setFlight(reservationDetails.getFlight());
            reservation.setReservationDate(reservationDetails.getReservationDate());
            reservation.setStatus(reservationDetails.getStatus());
            return toDTO(reservationRepository.save(reservation));
        } else {
            throw new AccessDeniedException("You do not have permission to update this reservation.");
        }
    }

    public void deleteReservation(Long id, Authentication authentication) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
            reservation.getUser().getUsername().equals(authentication.getName())) {
            reservationRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("You do not have permission to delete this reservation.");
        }
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