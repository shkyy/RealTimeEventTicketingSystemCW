package com.ticketing.system.backend.db_model;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table (name = "Ticket_Configuration")
@Entity
@Data
public class Ticket {

    @Id
    private long config_id;

    private int total_tickets;
    private int ticket_release_rate;
    private int max_ticket_capacity;
    private int customer_retrieval_rate;
}
