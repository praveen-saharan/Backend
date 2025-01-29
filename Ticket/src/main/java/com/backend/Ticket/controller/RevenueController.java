package com.backend.Ticket.controller;

import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.service.RevenueService;
import com.backend.Ticket.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @Autowired
    TransactionsService transactionsService;

    // New endpoint to handle calculation, saving, and returning revenue data
    @PostMapping("/calculate-and-save")
    public ResponseEntity<?> calculateAndSaveRevenue() {
        try {
            // Call the service method to delete old revenue, calculate new revenue, and save it
            return ResponseEntity.ok(revenueService.calculateAndSaveRevenue());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while calculating and saving revenue: " + e.getMessage());
        }
    }

    @GetMapping("/station/{stationId}/transactions")
    public ResponseEntity<List<Transactions>> getTransactionsForStationOnDate(
            @PathVariable Long stationId,
            @RequestParam("date") String date) {
        try {
            // Parse the date to LocalDateTime (start of the day and end of the day)
            LocalDate localDate = LocalDate.parse(date);  // Parses the date
            LocalDateTime startOfDay = localDate.atStartOfDay();
            LocalDateTime endOfDay = localDate.atTime(23, 59, 59);

            // Get all transactions for the station on the given date
            List<Transactions> transactions = revenueService.getTransactionsForStationOnDate(stationId, startOfDay, endOfDay);

            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            // If parsing fails, return a bad request response
            return ResponseEntity.badRequest().build();
        }
    }
}
