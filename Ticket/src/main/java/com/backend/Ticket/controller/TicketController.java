package com.backend.Ticket.controller;

import com.backend.Ticket.entity.Ticket;
import com.backend.Ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // POST API to create a ticket

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        System.out.println("Received POST request with ticket: title=" + ticket.getTitle() +
                ", description=" + ticket.getDescription() +
                ", status=" + ticket.getStatus());
        return ticketService.createTicket(ticket);
    }


    // GET API to fetch all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
}
