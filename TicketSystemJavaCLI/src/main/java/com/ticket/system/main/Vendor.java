package com.ticket.system.main;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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
    public void run(){

        for (int i = 1; i < totalSellingTickets; i++) {
            Ticket ticket = new Ticket(i,"Event", new BigDecimal("1000"));
            ticketPool.addTickets(ticket);
            try {
                Thread.sleep(releaseInterval * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

//        try {
//            int ticketId = 1;
//
//            while(true) {
//
//                for(int i = 1; i < totalSellingTickets; i++) {
//                    Ticket ticket = new Ticket(ticketId++, "Event", new BigDecimal("500.00"));
//                    ticketPool.addTickets(ticket);
//                    System.out.println("Vendor: " + vendorId + ", released ticket: " + ticket);
//                }
//
//                TimeUnit.SECONDS.sleep(releaseInterval);
//            }
//        }
//        catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException(e);
//        }

    }
}