package com.backend.Ticket.service;

import com.backend.Ticket.entity.TrainStation;
import com.backend.Ticket.repository.TrainStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainStationService {

    @Autowired
    private TrainStationRepository trainStationRepository;

    // Add a train station
    public TrainStation addTrainStation(TrainStation trainStation) {
        return trainStationRepository.save(trainStation);
    }

    // Add multiple train stations
    public List<TrainStation> addMultipleTrainStations(List<TrainStation> trainStations) {
        return trainStationRepository.saveAll(trainStations);
    }

    // Get all train stations
    public List<TrainStation> getAllTrainStations() {
        return trainStationRepository.findAll();
    }

    public Double getTotalFareAmount() {
        List<TrainStation> stations = trainStationRepository.findAll();
        return stations.stream()
                .mapToDouble(TrainStation::getFareAmount)
                .sum();
    }

    // Get a train station by ID
    public Optional<TrainStation> getTrainStationById(Long id) {
        return trainStationRepository.findById(id);
    }

    // Update a train station
    public TrainStation updateTrainStation(Long id, TrainStation trainStation) {
        Optional<TrainStation> existingStation = trainStationRepository.findById(id);
        if (existingStation.isPresent()) {
            TrainStation updatedStation = existingStation.get();
            updatedStation.setStationName(trainStation.getStationName());
            updatedStation.setStopNumber(trainStation.getStopNumber());
            updatedStation.setFareAmount(trainStation.getFareAmount());
            return trainStationRepository.save(updatedStation);
        } else {
            throw new RuntimeException("Train station not found with ID: " + id);
        }
    }

    // Delete a train station by ID
    public void deleteTrainStation(Long id) {
        trainStationRepository.deleteById(id);
    }

    public String getStationNameById(Long stationId) {
        TrainStation trainStation = trainStationRepository.findById(stationId).orElse(null);
        return trainStation != null ? trainStation.getStationName() : "Unknown";
    }

    /**
     * Get the fare amount based on the destination name.
     * @param destinationName the name of the destination station.
     * @return the fare amount for the specified destination, or null if not found.
     */
    public double getFareAmountByStationName(String stationName) {
        // Find the train station by station name
        TrainStation station = trainStationRepository.findByStationName(stationName);

        if (station != null) {
            return station.getFareAmount();
        } else {
            // Handle the case where no station is found
            throw new RuntimeException("Station with name " + stationName + " not found.");
        }
    }

}
