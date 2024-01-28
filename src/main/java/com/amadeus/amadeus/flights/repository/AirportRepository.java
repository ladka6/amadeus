package com.amadeus.amadeus.flights.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.amadeus.flights.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, UUID>{
    
}
