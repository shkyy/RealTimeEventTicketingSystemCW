package com.ticketing.system.backend.websocket;

import com.ticketing.system.backend.class_model.Customer;
import com.ticketing.system.backend.class_model.TicketPool;
import com.ticketing.system.backend.class_model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketPool ticketPool;

    @Autowired
    SimpMessagingTemplate template;

    public void startThreads(int releaseRate, int retrievalRate, int maxTicketCapacity) {

        this.ticketPool = new TicketPool(maxTicketCapacity);

        Vendor[] vendors = new Vendor[2];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(releaseRate, ticketPool,retrievalRate);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[2]; // Creating array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(retrievalRate, ticketPool, 1);
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }

        notifyFrontend("Threads started for ticket management.");
    }

    public void notifyFrontend(String message) {

         template.convertAndSend("/topic/config", message);
    }

}
