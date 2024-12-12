package com.ticketing.system.backend.class_model;

import com.ticketing.system.backend.db_model.TicketTransactionLogModel;
import com.ticketing.system.backend.enums.EventName;
import com.ticketing.system.backend.enums.UserType;
import com.ticketing.system.backend.repo.TicketLogRepo;


import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final int releaseInterval;
    private final TicketPool ticketPool;

    private final int totalSellingTickets;

    private final TicketLogRepo logRepo;

    public Vendor(int releaseInterval, TicketPool ticketPool, int totalSellingTickets, TicketLogRepo logRepo) {
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
        this.totalSellingTickets = totalSellingTickets;
        this.logRepo = logRepo;
    }

    @Override
    public void run() {

        for (int i = 1; i < totalSellingTickets; i++) {
            Ticket ticket = new Ticket(i);
            ticketPool.addTickets(ticket);
            TicketTransactionLogModel ticketLog = new TicketTransactionLogModel(UserType.Vendor, "V" + (i + 1), "Ticket added", ticket.getEventName(), ticket.getTicketPrice());
            logRepo.save(ticketLog);

            try {
                Thread.sleep(releaseInterval * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}