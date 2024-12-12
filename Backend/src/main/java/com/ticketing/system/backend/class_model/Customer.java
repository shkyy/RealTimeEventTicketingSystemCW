package com.ticketing.system.backend.class_model;

import com.ticketing.system.backend.db_model.TicketTransactionLogModel;
import com.ticketing.system.backend.enums.EventName;
import com.ticketing.system.backend.enums.UserType;
import com.ticketing.system.backend.repo.TicketLogRepo;

import java.math.BigDecimal;

public class Customer implements Runnable {

    private int retrievalRate;
    private TicketPool ticketPool;
    private int ticketQuantity;
    private final TicketLogRepo logRepo;



    public Customer(int retrievalRate, TicketPool ticketPool, int ticketQuantity, TicketLogRepo logRepo) {
        this.retrievalRate = retrievalRate;
        this.ticketPool = ticketPool;
        this.ticketQuantity = ticketQuantity;
        this.logRepo = logRepo;
    }

    @Override
    public void run() {

        for (int i = 0; i < ticketQuantity; i++) {

            Ticket ticket = new Ticket(i);
            ticketPool.buyTicket();

            try {
                TicketTransactionLogModel ticketLog = new TicketTransactionLogModel(UserType.Customer, "C" + (i+1), "Ticket bought", ticket.getEventName(), ticket.getTicketPrice());
                logRepo.save(ticketLog);

            } catch (Exception e) {
                System.err.println("Error logging ticket addition: " + e.getMessage());
            }

            try {
                Thread.sleep(retrievalRate * 1000); // Retrieving delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}