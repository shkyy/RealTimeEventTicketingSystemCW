package com.ticketing.system.backend.class_model;

import java.util.*;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumTicketCapacity;

    public TicketPool(int maximumTicketCapacity) {
        this.ticketQueue = new LinkedList<>();
        this.maximumTicketCapacity = maximumTicketCapacity;
    }

    public synchronized void addTickets(Ticket ticket) {

        while (ticketQueue.size() >= maximumTicketCapacity) {
            System.out.println("Current pool is full! Vendor is waiting to add tickets.");
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        this.ticketQueue.add(ticket);
        notifyAll();
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + ", current ticket amount: " + ticketQueue.size());
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            System.out.println("No tickets available. Customer is waiting...");
            try {
                wait();
            } catch (InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + "tickets left: " + ticketQueue.size());
        return ticket;
    }

}
