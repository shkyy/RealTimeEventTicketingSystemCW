package com.ticket.system.main;
import java.util.concurrent.TimeUnit;

public class Vendor implements Runnable {
    private final String vendorId;
    private final int ticketsPerRelease;
    private final int releaseInterval;
    private final TicketPool ticketPool;

    public Vendor(String vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){

        try {
            while(true) {
                ticketPool.addTickets(ticketsPerRelease);
                System.out.println("Vendor " + vendorId + "released" + ticketsPerRelease);
                TimeUnit.SECONDS.sleep(releaseInterval);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}