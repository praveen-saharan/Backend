package com.backend.Ticket.repository;

import com.backend.Ticket.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    // Custom query to delete all revenue records (we'll use this in the service)
    void deleteAll();
}
