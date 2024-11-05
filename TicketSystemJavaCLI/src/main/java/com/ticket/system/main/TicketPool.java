package com.ticket.system.main;

public class TicketPool {
    private int tickets;
    private final int maxTickets;

    public TicketPool(int maxTickets) {
        this.tickets = 0;
        this.maxTickets = maxTickets;
    }

    public synchronized void addTickets(int ticketAmount) {
        try {
            while (tickets + ticketAmount > maxTickets) {
                System.out.println("Current pool is full! Vendor is waiting to add tickets.");
                wait();
            }
            tickets += ticketAmount;
            System.out.println(ticketAmount + " number of tickets added by vendor. Total tickets now: " + tickets);
            notifyAll();
        }
        catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    public synchronized void removeTicket() {
        try {
            while (tickets <= 0) {
                System.out.println("No tickets available. Customer is waiting...");
                wait();
            }
            tickets--;
            System.out.println("Ticket purchased by a customer. tickets left: " + tickets);
            notifyAll();
        }
        catch (InterruptedException e) {
            System.out.println("Error");
        }
    }

    public int getTickets() {
        return tickets;
    }

}