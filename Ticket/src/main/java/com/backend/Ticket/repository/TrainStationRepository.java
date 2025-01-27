package com.backend.Ticket.repository;
import com.backend.Ticket.entity.Revenue;

import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
}

