package com.amadeus.amadeus.flights.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.amadeus.flights.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeGreaterThanEqualAndArrivalTimeLessThanEqual(
    String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalTime);
}
