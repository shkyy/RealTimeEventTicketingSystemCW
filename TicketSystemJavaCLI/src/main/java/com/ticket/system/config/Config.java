package com.ticket.system.config;

public class Config {
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;
    private final int maxTicketCapacity;

    public Config(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets = validateNumber(totalTickets, "Total number of tickets");
        this.ticketReleaseRate = validateNumber(ticketReleaseRate, "Ticket release rate");
        this.customerRetrievalRate = validateNumber(customerRetrievalRate, "Customer retrieval rate");
        this.maxTicketCapacity = validateMaximumNumber(validateNumber(maxTicketCapacity," Maximum ticket capacity"), this.totalTickets);
    }

    private int validateNumber(int num, String description) {
        if (num <= 0) {
            throw new IllegalArgumentException(description + " must be greater than zero!");
        }
        return num;

    }

    private int validateMaximumNumber(int maxTicketCapacity, int totalTickets){
        if(maxTicketCapacity > totalTickets) {
            throw new IllegalArgumentException("Maximum ticket capacity cannot be greater than the total number of available tickets!");
        }
        return maxTicketCapacity;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}