package com.ticketing.system.backend.class_model;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final int releaseInterval;
    private final TicketPool ticketPool;

    private final int totalSellingTickets;

    public Vendor(int releaseInterval, TicketPool ticketPool, int totalSellingTickets) {
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
        this.totalSellingTickets = totalSellingTickets;
    }

    @Override
    public void run() {

        for (int i = 1; i < totalSellingTickets; i++) {
            Ticket ticket = new Ticket(i, "Event", new BigDecimal("1000"));
            ticketPool.addTickets(ticket);
            try {
                Thread.sleep(releaseInterval * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}