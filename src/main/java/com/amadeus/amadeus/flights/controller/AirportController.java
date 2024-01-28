package com.amadeus.amadeus.flights.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.amadeus.flights.model.Airport;
import com.amadeus.amadeus.flights.service.AirportService;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable UUID id) {
        return airportService.getAirportById(id);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable UUID id, @RequestBody Airport updatedAirport) {
        return airportService.updateAirport(id, updatedAirport);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable UUID id) {
        airportService.deleteAirport(id);
    }

}