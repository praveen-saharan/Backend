//package com.backend.Ticket.controller;
//
//import com.backend.Ticket.entity.Transactions;
//import com.backend.Ticket.service.TransactionsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/transactions")
//public class TransactionsController {
//
//    @Autowired
//    private TransactionsService transactionsService;
//
//    // Create a new transaction
//    @PostMapping
//    public ResponseEntity<Transactions> createTransaction(@RequestBody Transactions transaction) {
//        Transactions savedTransaction = transactionsService.addTransaction(transaction);
//        return ResponseEntity.ok(savedTransaction);
//    }
//
//    // Get all transactions
//    @GetMapping
//    public ResponseEntity<List<Transactions>> getAllTransactions() {
//        List<Transactions> transactions = transactionsService.getAllTransactions();
//        return ResponseEntity.ok(transactions);
//    }
//
//    // Get a transaction by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Transactions> getTransactionById(@PathVariable Long id) {
//        Optional<Transactions> transaction = transactionsService.getTransactionById(id);
//        return transaction.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Update an existing transaction
//    @PutMapping("/{id}")
//    public ResponseEntity<Transactions> updateTransaction(@PathVariable Long id, @RequestBody Transactions transactionDetails) {
//        try {
//            Transactions updatedTransaction = transactionsService.updateTransaction(id, transactionDetails);
//            return ResponseEntity.ok(updatedTransaction);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Delete a transaction by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
//        try {
//            transactionsService.deleteTransaction(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Get transactions by user firstname
//    @GetMapping("/user/{firstname}")
//    public ResponseEntity<List<Transactions>> getTransactionsByUserFirstname(@PathVariable String firstname) {
//        List<Transactions> transactions = transactionsService.getTransactionsByUserFirstname(firstname);
//        return ResponseEntity.ok(transactions);
//    }
//
//    // Get transactions by train station ID
//    @GetMapping("/trainstation/{trainStationId}")
//    public ResponseEntity<List<Transactions>> getTransactionsByTrainStationId(@PathVariable Long trainStationId) {
//        List<Transactions> transactions = transactionsService.getTransactionsByTrainStationId(trainStationId);
//        return ResponseEntity.ok(transactions);
//    }
//
//    // Get transactions within a date range
//    @GetMapping("/date-range")
//    public ResponseEntity<List<Transactions>> getTransactionsWithinDateRange(@RequestParam("start") String start, @RequestParam("end") String end) {
//        try {
//            LocalDateTime startDate = LocalDateTime.parse(start);
//            LocalDateTime endDate = LocalDateTime.parse(end);
//            List<Transactions> transactions = transactionsService.getTransactionsWithinDateRange(startDate, endDate);
//            return ResponseEntity.ok(transactions);
//        } catch (DateTimeParseException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//}


package com.backend.Ticket.controller;

import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    // Create a new transaction
    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody Transactions transaction) {
        Transactions savedTransaction = transactionsService.addTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    // Get all transactions
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        List<Transactions> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Get a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable Long id) {
        Optional<Transactions> transaction = transactionsService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update an existing transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable Long id, @RequestBody Transactions transactionDetails) {
        try {
            Transactions updatedTransaction = transactionsService.updateTransaction(id, transactionDetails);
            return ResponseEntity.ok(updatedTransaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionsService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get transactions by user firstname
    @GetMapping("/user/{firstname}")
    public ResponseEntity<List<Transactions>> getTransactionsByUserFirstname(@PathVariable String firstname) {
        List<Transactions> transactions = transactionsService.getTransactionsByUserFirstname(firstname);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by train station ID
    @GetMapping("/trainstation/{trainStationId}")
    public ResponseEntity<List<Transactions>> getTransactionsByTrainStationId(@PathVariable Long trainStationId) {
        List<Transactions> transactions = transactionsService.getTransactionsByTrainStationId(trainStationId);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions within a date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Transactions>> getTransactionsWithinDateRange(@RequestParam("start") String start, @RequestParam("end") String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);
            List<Transactions> transactions = transactionsService.getTransactionsWithinDateRange(startDate, endDate);
            return ResponseEntity.ok(transactions);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Calculate and save revenue for each station
    @PostMapping("/calculate-revenue")
    public ResponseEntity<Void> calculateAndSaveRevenue() {
        transactionsService.calculateAndSaveRevenue();
        return ResponseEntity.ok().build();
    }
}
