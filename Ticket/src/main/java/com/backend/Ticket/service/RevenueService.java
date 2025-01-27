//package com.backend.Ticket.service;
//
//import com.backend.Ticket.entity.Revenue;
//import com.backend.Ticket.entity.TrainStation;
//import com.backend.Ticket.repository.RevenueRepository;
//import com.backend.Ticket.repository.TrainStationRepository;
//import com.backend.Ticket.repository.TransactionsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class RevenueService {
//
//    @Autowired
//    private TrainStationRepository trainStationRepository;
//    private TransactionsRepository
//
//    @Autowired
//    private RevenueRepository revenueRepository;
//
//    public void calculateRevenue() {
//        revenueRepository.deleteAll();
//        List<TrainStation> stations = trainStationRepository.findAll();
//
//        Map<String, Double> revenueMap = stations.stream().collect(
//                Collectors.groupingBy(
//                        TrainStation::getStationName,
//                        Collectors.summingDouble(TrainStation::getFareAmount)
//                )
//        );
//
//        revenueMap.forEach((stationName, revenue) -> {
//            Revenue revenueEntity = new Revenue();
//            revenueEntity.setStationName(stationName);
//            revenueEntity.setRevenue(revenue);
//            revenueRepository.save(revenueEntity);
//        });
//    }
//}