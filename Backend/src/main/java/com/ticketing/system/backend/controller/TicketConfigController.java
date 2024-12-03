package com.ticketing.system.backend.controller;

import com.ticketing.system.backend.class_model.ConfigSetting;
import com.ticketing.system.backend.db_model.TicketConfigSettingModel;
import com.ticketing.system.backend.repo.TicketConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketConfigController {

    @Autowired
    TicketConfigRepo repo;

    @PostMapping("/f")
    public String addConfiguration (@RequestBody TicketConfigSettingModel ticketConfig) {
        repo.save(ticketConfig);
        return "Config details save successfully";
    }
}
