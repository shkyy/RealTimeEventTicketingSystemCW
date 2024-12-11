package com.ticket.system.main;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private int retrievalRate;
    private TicketPool ticketPool;
    private  int ticketQuantity;


    public Customer(int retrievalRate, TicketPool ticketPool, int ticketQuantity) {
        this.retrievalRate = retrievalRate;
        this.ticketPool = ticketPool;
        this.ticketQuantity = ticketQuantity;
    }

    @Override
    public void run() {

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

            for (int i = 0; i < ticketQuantity; i++) {

                if (!Main.isRunning) break;

                ticketPool.buyTicket(); // Call method to buyTickets

                try {
                    Thread.sleep(retrievalRate * 1000); // Retrieving delay
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}