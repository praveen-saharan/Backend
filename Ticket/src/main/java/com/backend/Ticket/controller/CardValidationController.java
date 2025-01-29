package com.backend.Ticket.controller;

import com.backend.Ticket.service.CardValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/card")
public class CardValidationController {

    @Autowired
    private CardValidationService cardValidationService;

    @GetMapping("/validate")
    public boolean validateCard(@RequestParam String cardNumber) {
        return cardValidationService.validateCard(cardNumber);
    }

}