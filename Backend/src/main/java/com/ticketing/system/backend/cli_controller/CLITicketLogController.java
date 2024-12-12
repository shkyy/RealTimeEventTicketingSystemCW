package com.ticketing.system.backend.cli_controller;


import com.ticketing.system.backend.db_model.TicketTransactionLogModel;
import com.ticketing.system.backend.repo.TicketLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CLITicketLogController {
    @Autowired
    TicketLogRepo repo;

    @PostMapping("log")
    public String addLogDetails (@RequestBody TicketTransactionLogModel ticketLog) {
        repo.save(ticketLog);
        return "Ticket Log details saved successfully";
    }
}
