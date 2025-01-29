package com.backend.Ticket.service;

import com.backend.Ticket.entity.Transactions;
import com.backend.Ticket.exceptions.RecordNotFoundException;
import com.backend.Ticket.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TrainStationService trainStationService;

    // Service Method to Add a Transaction
    public Transactions addTransaction(Transactions transaction) {
        return transactionsRepository.save(transaction);
    }

    // Service Method to Get All Transactions
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    // Service Method to Get a Transaction by ID
    public Optional<Transactions> getTransactionById(Long transactionId) {
        return transactionsRepository.findById(transactionId);
    }

    // Service Method to Update a Transaction
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

    // Service Method to Delete a Transaction
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

    // Search for transactions by passenger last name
    public List<Transactions> searchByPassengerLastName(String lastName) {
        List<Transactions> transactions = transactionsRepository.findByUserLastnameIgnoreCase(lastName);

        if (transactions.isEmpty()) {
            throw new RecordNotFoundException("Passenger name " + lastName + " not found! Please try another name.");
        }

        return transactions;
    }
}
