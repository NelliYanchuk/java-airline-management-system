package dev.nyanchuk.airline.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import dev.nyanchuk.airline.airport.exception.AirportNotFoundException;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
    }

    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException(id));
        airport.setName(airportDetails.getName());
        airport.setLocation(airportDetails.getLocation());
        return airportRepository.save(airport);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}