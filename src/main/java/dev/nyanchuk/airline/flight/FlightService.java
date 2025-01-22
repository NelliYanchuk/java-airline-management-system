package dev.nyanchuk.airline.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.nyanchuk.airline.flight.exception.FlightNotFoundException;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = toEntity(flightDTO);
        return toDTO(flightRepository.save(flight));
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        return toDTO(flight);
    }

    public FlightDTO updateFlight(Long id, FlightDTO flightDetails) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        flight.setOrigin(flightDetails.getOrigin());
        flight.setDestination(flightDetails.getDestination());
        flight.setDepartureDate(flightDetails.getDepartureDate());
        flight.setArrivalDate(flightDetails.getArrivalDate());
        flight.setAvailableSeats(flightDetails.getAvailableSeats());
        flight.setStatus(flightDetails.isStatus());
        return toDTO(flightRepository.save(flight));
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    private FlightDTO toDTO(Flight flight) {
        return new FlightDTO(flight.getId(), flight.getOrigin(), flight.getDestination(),
                flight.getDepartureDate(), flight.getArrivalDate(), flight.getAvailableSeats(), flight.isStatus());
    }

    private Flight toEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setStatus(flightDTO.isStatus());
        return flight;
    }
}