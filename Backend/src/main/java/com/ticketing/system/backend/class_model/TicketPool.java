package com.ticketing.system.backend.class_model;

import com.ticketing.system.backend.enums.UserType;
import com.ticketing.system.backend.websocket.TicketService;

import java.util.*;
import java.util.function.Consumer;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumTicketCapacity;
    private Consumer<String> logListener; // Log listener to handle logs
    private final Runnable checkControlState;

    public TicketPool(int maximumTicketCapacity, Runnable checkControlState) {
        if (checkControlState == null) {
            throw new IllegalArgumentException("checkControlState cannot be null");
        }
        this.ticketQueue = new LinkedList<>();
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.checkControlState = checkControlState;
    }

    public void setLogListener(Consumer<String> logListener) {
        this.logListener = logListener;
    }

    public synchronized void addTickets(Ticket ticket) {
        checkControlState.run();

        while (ticketQueue.size() >= maximumTicketCapacity) {
            log("Current pool is full! Vendor is waiting to add tickets.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        this.ticketQueue.add(ticket);
        notifyAll();
        log("Ticket added by - " + Thread.currentThread().getName() + ", current ticket amount: " + ticketQueue.size());
    }

    public synchronized Ticket buyTicket() {
        checkControlState.run();

        while (ticketQueue.isEmpty()) {
            log("No tickets available. Customer is waiting...");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll();
        notifyAll();
        log("Ticket bought by - " + Thread.currentThread().getName() + "tickets left: " + ticketQueue.size());
        return ticket;
    }

    private void log(String message) {
        if (logListener != null) {
            logListener.accept(message); // Forward log messages to the listener
        }
    }
}