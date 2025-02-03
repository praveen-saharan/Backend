package com.backend.Ticket.controller;

import com.backend.Ticket.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = adminService.authenticate(username, password);

        if (authenticated) {
            return ResponseEntity.ok("Login successful!"); // 200 OK for success
        } else {
            return ResponseEntity.status(401).body("Invalid credentials!"); // 401 Unauthorized for failed login
        }
    }
}
