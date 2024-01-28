package com.amadeus.amadeus.flights.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.amadeus.flights.model.Flight;
import com.amadeus.amadeus.flights.repository.FlightRepository;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String departureCity, String arrivalCity, String departureDate,
            String returnDate) {
        LocalDateTime departureDateTime = LocalDateTime.parse(departureDate);
        LocalDateTime returnDateTime = (returnDate != null) ? LocalDateTime.parse(returnDate) : null;

        return flightRepository
                .findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeGreaterThanEqualAndArrivalTimeLessThanEqual(
                        departureCity, arrivalCity, departureDateTime, returnDateTime);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(UUID id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(UUID id, Flight updatedFlight) {
        Optional<Flight> existingFlightOptional = flightRepository.findById(id);

        if (existingFlightOptional.isPresent()) {
            Flight existingFlight = existingFlightOptional.get();
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
            existingFlight.setPrice(updatedFlight.getPrice());

            return flightRepository.save(existingFlight);
        }

        return null;
    }

    public void deleteFlight(UUID id) {
        flightRepository.deleteById(id);
    }
}
