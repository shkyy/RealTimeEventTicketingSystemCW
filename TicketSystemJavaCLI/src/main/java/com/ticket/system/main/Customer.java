package com.ticket.system.main;

import com.ticket.system.config.Config;

public class Customer implements Runnable{
    private final TicketPool ticketPool;
    private final int customerId;
    private final int retrievalInterval;

    public Customer(TicketPool ticketPool, int customerId, Config config) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.retrievalInterval = config.getCustomerRetrievalRate();
    }

    @Override
    public void run() {

    }
}
