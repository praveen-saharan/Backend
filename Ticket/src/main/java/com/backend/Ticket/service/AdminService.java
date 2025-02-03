package com.backend.Ticket.service;

import com.backend.Ticket.entity.Admin;
import com.backend.Ticket.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Simple authentication by comparing plain text password
    public boolean authenticate(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return admin.getPassword().equals(password); // Direct comparison (no hashing)
        }
        return false;  // Admin not found
    }
}