package com.backend.Ticket.service;

import com.backend.Ticket.entity.TrainStation;
import com.backend.Ticket.repository.TicketRepository;
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

     // Get sum of revenue of all the stations
//     public Double getTotalRevenue() {
//        System.out.println("command run successfully");
//         return trainStationRepository.findTotalRevenue();
//     }
}
