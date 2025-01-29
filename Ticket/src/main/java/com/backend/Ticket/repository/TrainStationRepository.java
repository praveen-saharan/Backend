package com.backend.Ticket.repository;

import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
//    Optional<TrainStation> findByDestinationName(String stationName);
    TrainStation findByStationName(String stationName);
    // Custom query methods (if any) can go here
}
