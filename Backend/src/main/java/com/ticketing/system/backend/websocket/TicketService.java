package com.ticketing.system.backend.websocket;

import com.ticketing.system.backend.class_model.Customer;
import com.ticketing.system.backend.class_model.TicketPool;
import com.ticketing.system.backend.class_model.Vendor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketPool ticketPool;

    private final SimpMessagingTemplate template;

    public TicketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void startThreads(int releaseRate, int retrievalRate, int maxTicketCapacity) {

        this.ticketPool = new TicketPool(maxTicketCapacity);

        // Set log listener to send logs via WebSocket
        ticketPool.setLogListener(logMessage ->
                template.convertAndSend("/topic/tickets", logMessage)
        );

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

    }


}
