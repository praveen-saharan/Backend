//package com.backend.Ticket.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import java.time.LocalDateTime;
//
//@Entity
//public class Transactions {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long transactionId;
//    private String userFirstname;
//    private String userLastname;
//    private Long trainStationId;
//    private String modeOfPayment;
//    private LocalDateTime transactionDateTime;
//    private double amount;
//
//    // Default constructor
//    public Transactions() {
//    }
//
//    // Getters and Setters
//    public Long getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(Long transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public String getUserFirstname() {
//        return userFirstname;
//    }
//
//    public void setUserFirstname(String userFirstname) {
//        this.userFirstname = userFirstname;
//    }
//
//    public String getUserLastname() {
//        return userLastname;
//    }
//
//    public void setUserLastname(String userLastname) {
//        this.userLastname = userLastname;
//    }
//
//    public Long getTrainStationId() {
//        return trainStationId;
//    }
//
//    public void setTrainStationId(Long trainStationId) {
//        this.trainStationId = trainStationId;
//    }
//
//    public String getModeOfPayment() {
//        return modeOfPayment;
//    }
//
//    public void setModeOfPayment(String modeOfPayment) {
//        this.modeOfPayment = modeOfPayment;
//    }
//
//    public LocalDateTime getTransactionDateTime() {
//        return transactionDateTime;
//    }
//
//    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
//        this.transactionDateTime = transactionDateTime;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//}


package com.backend.Ticket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String userFirstname;
    private String userLastname;
    private Long trainStationId;
    private String modeOfPayment;
    private LocalDateTime transactionDateTime;
    private double amount;

    // Default constructor
    public Transactions() {
        this.transactionDateTime = LocalDateTime.now(); // Automatically set the current date and time
    }

    // Getters and setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public Long getTrainStationId() {
        return trainStationId;
    }

    public void setTrainStationId(Long trainStationId) {
        this.trainStationId = trainStationId;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
