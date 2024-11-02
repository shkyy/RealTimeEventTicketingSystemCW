package com.ticket.system.config;

public class Config {
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;
    private final int maxTicketCapacity;

    public Config(String totalTickets, String ticketReleaseRate, String customerRetrievalRate, String maxTicketCapacity){
        this.totalTickets = validateNumber(totalTickets, "Total number of tickets");
        this.ticketReleaseRate = validateNumber(ticketReleaseRate, "Ticket release rate");
        this.customerRetrievalRate = validateNumber(customerRetrievalRate, "Customer retrieval rate");
        this.maxTicketCapacity = validateMaximumNumber(validateNumber(maxTicketCapacity," Maximum ticket capacity"), this.totalTickets);
    }

    private int validateNumber(String value, String description) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(description + " cannot be null!");
        }
        try{
            int num = Integer.parseInt(value);
            if (num <= 0) {
                throw new IllegalArgumentException(description + " must be greater than zero!");
            }
            return num;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(description + " invalid integer!");
        }
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