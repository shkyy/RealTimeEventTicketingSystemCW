package com.ticketing.system.backend.db_model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Table (name = "Ticket_Configuration")
@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long config_id;

    private int totalTickets;
    private int ticketReleaseRate;
    private int maxTicketCapacity;
    private int customerRetrievalRate;

}
