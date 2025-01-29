package com.backend.Ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketSystemApplication.class, args);

//		CardValidationService.luhnTest("49927398716");
	}

}