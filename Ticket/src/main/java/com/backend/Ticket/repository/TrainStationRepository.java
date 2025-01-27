package com.backend.Ticket.repository;

import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
    // Custom query methods (if any) can go here
}
