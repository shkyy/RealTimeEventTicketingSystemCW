package com.ticket.system.main;

import com.ticket.system.main.enums.EventNames;

import java.math.BigDecimal;
import java.util.Random;

public class Ticket {
    private int ticketId;
    private EventNames eventName;
    private BigDecimal ticketPrice;

    private static final Random random = new Random();

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.eventName = getRandomEvent();
        this.ticketPrice = this.eventName.getPrice();
    }

    private EventNames getRandomEvent() {
        EventNames[] events = EventNames.values();
        return events[random.nextInt(events.length)];
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public EventNames getEventName() {
        return eventName;
    }

    public void setEventName(EventNames eventName) {
        this.eventName = eventName;
        this.ticketPrice = eventName.getPrice();
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
