package com.backend.Ticket.service;

import com.backend.Ticket.entity.Revenue;
import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.repository.RevenueRepository;
import com.backend.Ticket.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RevenueService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private TrainStationService trainStationService;

    // New method that deletes the old revenue data, calculates the new revenue, and saves it
    public List<Map<String, Object>> calculateAndSaveRevenue() {
        // Step 1: Delete existing revenue data
        revenueRepository.deleteAll();

        // Step 2: Calculate the new revenue for each station
        List<Transactions> transactions = transactionsRepository.findAll();

        // Group transactions by station ID
        Map<Long, List<Transactions>> stationGroupedTransactions = transactions.stream()
                .collect(Collectors.groupingBy(Transactions::getTrainStationId));

        // Prepare the result for revenue calculation
        List<Map<String, Object>> revenueReport = new ArrayList<>();

        for (Map.Entry<Long, List<Transactions>> entry : stationGroupedTransactions.entrySet()) {
            Long stationId = entry.getKey();
            List<Transactions> stationTransactions = entry.getValue();

            // Get the station name
            String stationName = trainStationService.getStationNameById(stationId);

            // Calculate total revenue (sum of amounts)
            double totalRevenue = stationTransactions.stream()
                    .mapToDouble(Transactions::getAmount)
                    .sum();

            // Calculate total passengers (count of transactions)
            int totalPassengers = stationTransactions.size();

            // Step 3: Save the calculated revenue to the Revenue table
            Revenue revenue = new Revenue();
            revenue.setTrainStationId(stationId);
            revenue.setTrainStationName(stationName);
            revenue.setTotalRevenue(totalRevenue);
            revenue.setTotalPassengers(totalPassengers);
            revenueRepository.save(revenue);

            // Add the revenue data to the report list
            Map<String, Object> stationRevenueMap = new HashMap<>();
            stationRevenueMap.put("trainId", stationId);
            stationRevenueMap.put("stationName", stationName);
            stationRevenueMap.put("earnings", totalRevenue);
            stationRevenueMap.put("totalPassengers", totalPassengers);

            revenueReport.add(stationRevenueMap);
        }

        // Step 4: Return the calculated revenue data
        return revenueReport;
    }

    // Get all transactions for a specific train station within a day
    public List<Transactions> getTransactionsForStationOnDate(Long stationId, LocalDateTime startOfDay, LocalDateTime endOfDay) {
        // Get all transactions for the given train station that occurred within the specified date range
        return transactionsRepository.findByTrainStationIdAndTransactionDateTimeBetween(stationId, startOfDay, endOfDay);
    }
}
