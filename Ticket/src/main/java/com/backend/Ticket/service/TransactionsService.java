package com.backend.Ticket.service;

import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    // Add a new transaction

    public Transactions addTransaction(Transactions transaction) {
        // Ensure the transaction date and time is set to the current time
        transaction.setTransactionDateTime(LocalDateTime.now());
        return transactionsRepository.save(transaction);
    }

    // Get all transactions
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    // Get a transaction by ID
    public Optional<Transactions> getTransactionById(Long transactionId) {
        return transactionsRepository.findById(transactionId);
    }

    // Update an existing transaction
    public Transactions updateTransaction(Long transactionId, Transactions transactionDetails) {
        Optional<Transactions> existingTransaction = transactionsRepository.findById(transactionId);
        if (existingTransaction.isPresent()) {
            Transactions updatedTransaction = existingTransaction.get();
            updatedTransaction.setUserFirstname(transactionDetails.getUserFirstname());
            updatedTransaction.setUserLastname(transactionDetails.getUserLastname());
            updatedTransaction.setTrainStationId(transactionDetails.getTrainStationId());
            updatedTransaction.setModeOfPayment(transactionDetails.getModeOfPayment());
            updatedTransaction.setTransactionDateTime(transactionDetails.getTransactionDateTime());
            updatedTransaction.setAmount(transactionDetails.getAmount());
            return transactionsRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found with ID: " + transactionId);
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(Long transactionId) {
        transactionsRepository.deleteById(transactionId);
    }

    // Get all transactions by user firstname
    public List<Transactions> getTransactionsByUserFirstname(String userFirstname) {
        return transactionsRepository.findByUserFirstname(userFirstname);
    }

    // Get all transactions by train station ID
    public List<Transactions> getTransactionsByTrainStationId(Long trainStationId) {
        return transactionsRepository.findByTrainStationId(trainStationId);
    }

    // Get all transactions within a date range (custom method)
    public List<Transactions> getTransactionsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionsRepository.findByTransactionDateTimeBetween(startDate, endDate);
    }
}
