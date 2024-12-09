package com.ticketing.system.backend.db_model;


import com.ticketing.system.backend.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Table(name="Ticket_Info_Log")
@Entity
@Data
public class TicketTransactionLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;

    private UserType userType;
    private String userId;
    private String action;
    private String eventName;

    public TicketTransactionLogModel() {}

    public TicketTransactionLogModel(UserType userType, String userId, String action, String eventName) {
        this.userType = userType;
        this.userId = userId;
        this.action = action;
        this.eventName = eventName;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
