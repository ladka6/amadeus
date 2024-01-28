package com.amadeus.amadeus.flights.controller;

import org.springframework.web.bind.annotation.RestController;

import com.amadeus.amadeus.flights.model.Flight;
import com.amadeus.amadeus.flights.service.FlightService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate) {
        return flightService.searchFlights(departureCity, arrivalCity, departureDate, returnDate);
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable UUID id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable UUID id, @RequestBody Flight updatedFlight) {
        return flightService.updateFlight(id, updatedFlight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
    }
}