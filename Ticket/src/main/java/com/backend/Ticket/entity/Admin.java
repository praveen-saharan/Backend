package com.backend.Ticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Admin {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    // Default constructor
    public Admin() {}

    // Parameterized constructor (optional)
    public Admin(String username, String password) {
        this.username = username;
        this.password = password; // Note: Password should be hashed before setting
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Instead of setting the password directly, you could hash it before setting
    public void setPassword(String password) {
        this.password = password;
    }

    // Helper method to hash the password before saving it

}
