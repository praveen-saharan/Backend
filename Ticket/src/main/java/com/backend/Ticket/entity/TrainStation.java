package com.backend.Ticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TrainStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;
    private int stopNumber;
    private double fareAmount;

    // Default constructor
    public TrainStation() {
    }

    // Getters and Setters
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(double fareAmount) {
        this.fareAmount = fareAmount;
    }
}
