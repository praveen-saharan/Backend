package com.backend.Ticket.repository;

import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.Ticket.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}