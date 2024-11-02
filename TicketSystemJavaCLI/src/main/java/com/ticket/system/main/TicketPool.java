package com.ticket.system.main;
import java.util.concurrent.locks.ReentrantLock;
import com.ticket.system.config.Config;

public class TicketPool {
    private int currentNoOfTickets = 0;
    private int maxTicketCapacity;

    public TicketPool(Config config) {
        this.maxTicketCapacity = config.getMaxTicketCapacity();
    }

    public int getCurrentNoOfTickets() {
        return currentNoOfTickets;
    }

    public boolean addTicket() {
        if (currentNoOfTickets < maxTicketCapacity) {
            currentNoOfTickets++;
            System.out.println("Ticket added by vendor");
            return true;
        }
        return false;
    }

    public boolean removeTicker() {
        if (currentNoOfTickets > 0) {
            currentNoOfTickets--;
            System.out.println("Ticker purchased by customer");
            return true;
        }
        return false;
    }
}
