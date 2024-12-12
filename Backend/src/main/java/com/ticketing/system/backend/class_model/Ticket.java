package com.ticketing.system.backend.class_model;

import com.ticketing.system.backend.enums.EventName;

import java.math.BigDecimal;
import java.util.Random;

public class Ticket {
    private int ticketId;
    private EventName eventName;
    private BigDecimal ticketPrice;

    private static final Random random = new Random();

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.eventName = getRandomEvent();
        this.ticketPrice = this.eventName.getPrice();
    }

    private EventName getRandomEvent() {
        EventName[] events = EventName.values();
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

    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
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
