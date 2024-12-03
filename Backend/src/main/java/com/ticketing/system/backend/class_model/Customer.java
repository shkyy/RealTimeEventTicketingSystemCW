package com.ticketing.system.backend.class_model;

public class Customer implements Runnable {
    private int retrievalRate;
    private TicketPool ticketPool;
    private int ticketQuantity;


    public Customer(int retrievalRate, TicketPool ticketPool, int ticketQuantity) {
        this.retrievalRate = retrievalRate;
        this.ticketPool = ticketPool;
        this.ticketQuantity = ticketQuantity;
    }

    @Override
    public void run() {

        for (int i = 0; i < ticketQuantity; i++) {
            Ticket ticket = ticketPool.buyTicket(); // Call method to buyTickets
            System.out.println("Ticket is - " + ticket + " - Customer name is - " + Thread.currentThread().getName());
            try {
                Thread.sleep(retrievalRate * 1000); // Retrieving delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}