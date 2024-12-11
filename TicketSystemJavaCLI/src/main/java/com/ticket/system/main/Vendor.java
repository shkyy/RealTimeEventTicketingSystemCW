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
}