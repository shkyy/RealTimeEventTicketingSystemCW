package com.ticketing.system.backend.websocket;

import com.ticketing.system.backend.class_model.Customer;
import com.ticketing.system.backend.class_model.TicketPool;
import com.ticketing.system.backend.class_model.Vendor;
import com.ticketing.system.backend.repo.TicketLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TicketService {
    private TicketPool ticketPool;
    private final SimpMessagingTemplate template;
    private boolean isPaused = false;
    private boolean isStopped = false;

    private Thread[] vendorThreads;
    private Thread[] customerThreads;

    public TicketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    TicketLogRepo logRepo;

    public void startThreads(int releaseRate, int retrievalRate, int maxTicketCapacity) {

        Random random = new Random();
        int number = random.nextInt(10);

        stopThreads(); // Ensure old threads are stopped before starting new ones
        isPaused = false;
        isStopped = false;

        ticketPool = new TicketPool(maxTicketCapacity, this::checkPauseAndStop);
        ticketPool.setLogListener(logMessage -> template.convertAndSend("/topic/tickets", logMessage));

        Vendor[] vendors = new Vendor[number];
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(releaseRate, ticketPool,retrievalRate, logRepo);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[number]; // Creating array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(retrievalRate, ticketPool, number , logRepo);
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }
    }

    public void pauseThreads() {
        isPaused = true;
    }

    public synchronized void resumeThreads() {
        isPaused = false;
        notifyAll();
    }

    public synchronized void stopThreads() {
        isStopped = true;
        interruptThreads(vendorThreads);
        interruptThreads(customerThreads);
    }

    private void interruptThreads(Thread[] threads) {
        if (threads != null) {
            for (Thread thread : threads) {
                if (thread != null && thread.isAlive()) {
                    thread.interrupt();
                }
            }
        }
    }

    public synchronized void checkPauseAndStop() {
        while (isPaused) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (isStopped) {
            throw new RuntimeException("Operation stopped");
        }
    }
}
