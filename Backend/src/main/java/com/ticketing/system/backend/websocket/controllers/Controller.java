package com.ticketing.system.backend.websocket.controllers;

import com.ticketing.system.backend.class_model.ConfigSetting;
import com.ticketing.system.backend.db_model.TicketConfigSettingModel;
import com.ticketing.system.backend.repo.TicketConfigRepo;
import com.ticketing.system.backend.websocket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    TicketService ticketService;
    @Autowired
    TicketConfigRepo repo;

    private final List<ConfigSetting> configParams;

    public Controller(){
        configParams = new ArrayList<ConfigSetting>();
    }
    @MessageMapping("/config")
    @SendTo("/topic/tickets")
    public ConfigSetting config (@RequestBody ConfigSetting config) {
        configParams.add(config);

        TicketConfigSettingModel db_model = new TicketConfigSettingModel();
        db_model.setTotalTickets(config.getTotalTickets());
        db_model.setTicketReleaseRate(config.getTicketReleaseRate());
        db_model.setCustomerRetrievalRate(config.getCustomerRetrievalRate());
        db_model.setMaxTicketCapacity(config.getMaxTicketCapacity());

        repo.save(db_model);

        return config;
    }

}
