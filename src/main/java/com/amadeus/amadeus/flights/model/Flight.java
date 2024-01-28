package com.amadeus.amadeus.flights.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;
    
    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalTime;

    @Column(name = "price")
    private Double price;

    //Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departure_airport) {
        this.departureAirport = departure_airport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrival_airport) {
        this.arrivalAirport = arrival_airport;
    }

    public LocalDateTime getDepartureTime() {
        return departureDateTime;
    }

    public void setDepartureTime(LocalDateTime departure_time) {
        this.departureDateTime = departure_time;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
