package com.backend.Ticket.controller;

import com.backend.Ticket.entity.TrainStation;
import com.backend.Ticket.service.TicketService;
import com.backend.Ticket.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainstations")
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;

    // Create a new train station
    @PostMapping
    public TrainStation createTrainStation(@RequestBody TrainStation trainStation) {
        return trainStationService.addTrainStation(trainStation);
    }

    // Get all train stations
    @GetMapping
    public List<TrainStation> getAllTrainStations() {
        return trainStationService.getAllTrainStations();
    }

    @GetMapping("/total-revenue")
    public Double getTotalRevenue() {
        return trainStationService.getTotalRevenue();
    }
}
