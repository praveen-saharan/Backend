package com.backend.Ticket.service;

import com.backend.Ticket.entity.Ticket;
import com.backend.Ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;


    public Ticket createTicket(Ticket ticket) {
        System.out.println("Creating ticket: " + ticket);
        Ticket savedTicket = ticketRepository.save(ticket);
        System.out.println("Saved ticket: " + savedTicket);
        return savedTicket;
    }


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
