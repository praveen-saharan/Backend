package com.backend.Ticket.repository;

import com.backend.Ticket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation, Long> {
    // Custom query methods (if any) can go here
//    @Query("SELECT SUM(p.fareAmount) FROM train_station p")
//    Double findTotalRevenue();
}
