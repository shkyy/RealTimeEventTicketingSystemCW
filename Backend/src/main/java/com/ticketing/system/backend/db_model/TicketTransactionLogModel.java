package com.ticketing.system.backend.db_model;


import com.ticketing.system.backend.enums.EventName;
import com.ticketing.system.backend.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Table(name="Ticket_Info_Log")
@Entity
@Data
public class TicketTransactionLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String userId;
    private String action;

    @Enumerated(EnumType.STRING)
    private EventName eventName;

    private BigDecimal ticketPrice;

    public TicketTransactionLogModel() {}

    public TicketTransactionLogModel(UserType userType, String userId, String action, EventName eventName, BigDecimal ticketPrice) {
        this.userType = userType;
        this.userId = userId;
        this.action = action;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
