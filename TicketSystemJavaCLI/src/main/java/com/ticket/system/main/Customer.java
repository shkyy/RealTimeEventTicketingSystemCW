package com.ticket.system.main;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private final String customerId;
    private final int retrievalInterval;
    private final TicketPool ticketPool;


    public Customer(String customerId, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {

        try {
            while(true) {
                ticketPool.removeTicket();
                System.out.println("Customer " + customerId + "purchased a ticket");
                TimeUnit.SECONDS.sleep(retrievalInterval);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}