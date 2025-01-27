package com.backend.Ticket.repository;

import com.backend.Ticket.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    // Find transactions by user firstname
    List<Transactions> findByUserFirstname(String userFirstname);

    // Find transactions by train station ID
    List<Transactions> findByTrainStationId(Long trainStationId);

    // Find transactions within a specific date range
    List<Transactions> findByTransactionDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
