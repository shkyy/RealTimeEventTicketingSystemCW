package com.ticketing.system.backend.websocket.controllers;

import com.ticketing.system.backend.class_model.ConfigSetting;
import com.ticketing.system.backend.db_model.TicketConfigSettingModel;
import com.ticketing.system.backend.repo.TicketConfigRepo;
import com.ticketing.system.backend.websocket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    TicketService ticketService;
    @Autowired
    TicketConfigRepo repo;

    @MessageMapping("/config")
    public void config (@RequestBody ConfigSetting config) {

        TicketConfigSettingModel db_model = new TicketConfigSettingModel();
        db_model.setTotalTickets(config.getTotalTickets());
        db_model.setTicketReleaseRate(config.getTicketReleaseRate());
        db_model.setCustomerRetrievalRate(config.getCustomerRetrievalRate());
        db_model.setMaxTicketCapacity(config.getMaxTicketCapacity());

        repo.save(db_model);

        ticketService.startThreads(
            config.getTicketReleaseRate(),
            config.getCustomerRetrievalRate(),
            config.getMaxTicketCapacity()
        );

    }

    // Pause threads
    @MessageMapping("/pause")
    public void pause() {
        ticketService.pauseThreads();
    }

    // Resume threads
    @MessageMapping("/resume")
    public void resume() {
        ticketService.resumeThreads();
    }

    // Stop threads
    @MessageMapping("/stop")
    public void stop() {
        ticketService.stopThreads();
    }

}
