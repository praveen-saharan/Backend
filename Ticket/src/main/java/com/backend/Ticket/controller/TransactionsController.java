package com.backend.Ticket.controller;

import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.exceptions.RecordNotFoundException;
import com.backend.Ticket.service.TrainStationService;
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

    @Autowired
    private CardValidationService cardValidationService;

    @Autowired TrainStationService trainStationService;

    // Create a new transaction after validating card details
    @PostMapping("/add-transaction")
    public ResponseEntity<String> createTransaction(@RequestBody Transactions transaction) {
        // Validate card number if mode of payment is "Card"
        if ("Card".equalsIgnoreCase(transaction.getModeOfPayment())) {
            boolean isValidCard = cardValidationService.validateCard(transaction.getCardNumber());
            if (!isValidCard) {
                System.out.println("Invalid card number");
                return ResponseEntity.badRequest().body("Invalid card number.");
            }
        }
        // Validate cash payment
        else if ("Cash".equalsIgnoreCase(transaction.getModeOfPayment())) {
            // Ensure the amount is greater than or equal to the fare amount
            double fareAmount = trainStationService.getFareAmountByStationName(transaction.getDestinationName());

            if (transaction.getAmount() < fareAmount) {
                return ResponseEntity.badRequest().body("Invalid amount. The amount should be greater than or equal to the fare amount.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid payment method. Please use 'Card' or 'Cash'.");
        }

        // Proceed to save the transaction if valid
        Transactions savedTransaction = transactionsService.addTransaction(transaction);

        // Returning the saved transaction in response
        return ResponseEntity.ok("Transaction successfully added: " + savedTransaction.getTransactionId());
    }



    // Get all transactions
    @GetMapping("/all-transactions")
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

    // Search for passenger by last name
    @GetMapping("/search-passenger")
    public ResponseEntity<List<Transactions>> searchPassenger(@RequestParam("lastname") String lastName) {
        try {
            List<Transactions> transactions = transactionsService.searchByPassengerLastName(lastName);
            return ResponseEntity.ok(transactions);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.status(404).body(null); // Return 404 when not found
        }
    }
}
