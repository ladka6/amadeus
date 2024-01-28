package com.amadeus.amadeus.flights.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.amadeus.flights.model.Airport;
import com.amadeus.amadeus.flights.repository.AirportRepository;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(UUID id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(UUID id, Airport updatedAirport) {
        Optional<Airport> existingAirportOptional = airportRepository.findById(id);

        if (existingAirportOptional.isPresent()) {
            Airport existingAirport = existingAirportOptional.get();
            existingAirport.setCity(updatedAirport.getCity());

            return airportRepository.save(existingAirport);
        }

        return null;
    }

    public void deleteAirport(UUID id) {
        airportRepository.deleteById(id);
    }
}
