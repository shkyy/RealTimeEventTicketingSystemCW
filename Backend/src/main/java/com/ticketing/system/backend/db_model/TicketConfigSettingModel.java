package com.ticketing.system.backend.db_model;
import jakarta.persistence.*;
import lombok.Data;


@Table (name = "Ticket_Configuration")
@Entity
@Data
public class TicketConfigSettingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long config_id;

    private int totalTickets;
    private int ticketReleaseRate;
    private int maxTicketCapacity;
    private int customerRetrievalRate;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }


}
