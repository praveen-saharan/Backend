package com.backend.Ticket.controller;

import com.backend.Ticket.entity.TrainStation;
import com.backend.Ticket.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.backend.Ticket.exception.InvalidFileUploadException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/trainstations")
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;

    // Create a new train station
    @PostMapping
    public TrainStation createTrainStation(@RequestBody TrainStation trainStation) {
        return trainStationService.addTrainStation(trainStation);
    }

    @PostMapping("/bulk")
    public List<TrainStation> createMultipleTrainStations(@RequestBody List<TrainStation> trainStations) {
        return trainStationService.addMultipleTrainStations(trainStations);
    }

    // Get all train stations
    @GetMapping
    public List<TrainStation> getAllTrainStations() {
        return trainStationService.getAllTrainStations();
    }

    @GetMapping("/total-fare")
    public Double getTotalFareAmount() {
        return trainStationService.getTotalFareAmount();
    }

    // Handle file upload (CSV or TXT)
    @PostMapping("/upload")
    public List<TrainStation> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new InvalidFileUploadException("No file uploaded.");
        }

        try {
            String fileName = file.getOriginalFilename();
            List<TrainStation> trainStations = new ArrayList<>();

            if (fileName != null && fileName.endsWith(".csv")) {
                trainStations = parseCSVFile(file);
            } else if (fileName != null && fileName.endsWith(".txt")) {
                trainStations = parseTXTFile(file);
            } else {
                throw new InvalidFileUploadException("Only CSV and TXT files are supported.");
            }

            // Save the parsed train stations to the DB
            return trainStationService.addMultipleTrainStations(trainStations);

        } catch (IOException e) {
            throw new InvalidFileUploadException("Error reading file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new InvalidFileUploadException("An error occurred while processing the file: " + e.getMessage(), e);
        }
    }


    // Parse CSV file (previous implementation remains the same)
    private List<TrainStation> parseCSVFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<TrainStation> trainStations = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            if (fields.length != 5) {
                throw new InvalidFileUploadException("CSV file must have 5 columns: stopNumber, stationName, fareAmount, sourceStation, duration.");
            }

            try {
                TrainStation trainStation = new TrainStation();
                trainStation.setStopNumber(Integer.parseInt(fields[0].trim()));
                trainStation.setStationName(fields[1].trim());
                trainStation.setFareAmount(Double.parseDouble(fields[2].trim()));
                trainStation.setSourceStation(fields[3].trim());
                trainStation.setDuration(Double.parseDouble(fields[4].trim()));
                trainStations.add(trainStation);
            } catch (Exception e) {
                throw new InvalidFileUploadException("Invalid data found in CSV file: " + e.getMessage());
            }
        }
        reader.close();
        return trainStations;
    }


    // Parse TXT file
    private List<TrainStation> parseTXTFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<TrainStation> trainStations = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("\\s+"); // Split by one or more spaces

            if (fields.length != 5) {
                throw new InvalidFileUploadException("TXT file must have 5 columns: stopNumber, stationName, fareAmount, sourceStation, duration.");
            }

            try {
                TrainStation trainStation = new TrainStation();
                trainStation.setStopNumber(Integer.parseInt(fields[0].trim()));
                trainStation.setStationName(fields[1].trim());
                trainStation.setFareAmount(Double.parseDouble(fields[2].trim()));
                trainStation.setSourceStation(fields[3].trim());
                trainStation.setDuration(Double.parseDouble(fields[4].trim()));
                trainStations.add(trainStation);
            } catch (Exception e) {
                throw new InvalidFileUploadException("Invalid data found in TXT file: " + e.getMessage());
            }
        }
        reader.close();
        return trainStations;
    }
}
