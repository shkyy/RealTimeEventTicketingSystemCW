package com.ticketing.system.backend.controller;

import com.ticketing.system.backend.db_model.Ticket;
import com.ticketing.system.backend.repo.TicketConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketConfigController {

    @Autowired
    TicketConfigRepo repo;

    @PostMapping("/f")
    public String addConfiguration (@RequestBody Ticket ticketConfig) {
        repo.save(ticketConfig);
        return "Config details save successfully";
    }
}
