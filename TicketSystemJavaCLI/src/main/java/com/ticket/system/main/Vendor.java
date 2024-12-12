package com.ticket.system.main;
import com.ticket.system.clients.TicketLogClient;
import com.ticket.system.main.enums.UserType;

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

        while (true) {
            synchronized (this) {
                while (!Main.isRunning) { // Check the running state
                    try {
                        wait(); // Wait for resume notification
                    } catch (InterruptedException e) {
                        return; // Exit thread if interrupted
                    }
                }
            }

            for (int i = 1; i < totalSellingTickets; i++) {

                if (!Main.isRunning) break;

                Ticket ticket = new Ticket(i);
                ticketPool.addTickets(ticket);
                // Log the action
                try {
                    TicketLogClient.sendTicketLog(UserType.Vendor, "V" + (i+1), "Ticket added", ticket.getEventName(), ticket.getTicketPrice());
                } catch (Exception e) {
                    System.err.println("Error logging ticket addition: " + e.getMessage());
                }

                try {
                    Thread.sleep(releaseInterval * 1000); // To calculate to MS
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}