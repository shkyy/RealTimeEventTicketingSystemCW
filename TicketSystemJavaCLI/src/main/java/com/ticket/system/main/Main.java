package com.ticket.system.main;

import com.ticket.system.config.Config;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your vendor ID: ");
        String vendorId = scanner.nextLine();

        System.out.print("Enter total number of tickets: ");
        int totalTickets = scanner.nextInt();

        System.out.print("Enter ticket release rate: ");
        int ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter customer retrieval rate: ");
        int customerRetrievalRate = scanner.nextInt();

        System.out.print("Enter maximum ticket capacity: ");
        int maxTicketCapacity = scanner.nextInt();

        try {
            Config config = new Config(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
            TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

            // Start vendor and customer threads for testing
            Thread vendor = new Thread(new Vendor(vendorId, config.getTicketReleaseRate(), 5, ticketPool));
            Thread customer = new Thread(new Customer("Customer1", config.getCustomerRetrievalRate(), ticketPool));

            vendor.start();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

