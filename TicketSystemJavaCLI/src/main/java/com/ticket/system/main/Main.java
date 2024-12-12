package com.ticket.system.main;

import com.ticket.system.clients.TicketConfigClient;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static volatile boolean isRunning = false;
    public  static void main(String[] args) throws Exception {

        int totalTickets = 0;

        System.out.println("\n========== Real-Time Ticketing System ==========\n");
        totalTickets = getValidNumber(totalTickets, "the total number tickets");
        int maxTicketCapacity = getValidNumber(totalTickets, "the maximum number tickets");
        int ticketReleaseRate = getValidNumber(totalTickets, "the ticket release rate");
        int customerRetrievalRate = getValidNumber(totalTickets, "the customer retrieval rate");
        int noOfVendors = getValidNumber(totalTickets, "the number of vendors");
        int noOfCustomers = getValidNumber(totalTickets, "the number of customers");

        TicketConfigClient.sendConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        Config config = new Config(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);


        Random random = new Random();
        int ticketQuantity = random.nextInt(4);

        try {
            TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

            Vendor[] vendors = new Vendor[noOfVendors];
            Thread[] vendorThreads = new Thread[noOfVendors];
            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(config.getTicketReleaseRate(), ticketPool, config.getTotalTickets());
                vendorThreads[i] = new Thread(vendors[i], "Vendor ID- " + i);
            }

            Customer[] customers = new Customer[noOfCustomers];
            Thread[] customerThreads = new Thread[noOfCustomers];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(config.getCustomerRetrievalRate(), ticketPool, ticketQuantity);
                customerThreads[i] = new Thread(customers[i], "Customer ID- " + i);
            }

            // Start listening for commands
            listenCommands(vendorThreads, customerThreads);
        } catch (IllegalArgumentException e) {
            System.out.println("[Error]: " + e.getMessage());
        }
    }
    private static int getValidNumber(int totalTickets, String valueDescription) {
        while(true) {
            System.out.println("Enter " + valueDescription + ":");
            try {
                int value = scanner.nextInt();
                scanner.nextLine();

                if (Objects.equals(valueDescription, "the maximum number tickets")) {
                    value = Config.isValidNumber(value, "Maximum number of tickets");
                    Config.isValidMaximumNumber(value, totalTickets);

                    return value;
                }
                else {
                    return Config.isValidNumber(value, valueDescription);
                }
            }
            catch (InputMismatchException e) {
                System.out.println("[Error] Invalid input! Please enter an integer");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e){
                System.out.println("[Error] Please enter again." + e.getMessage());
            }
        }
    }

    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }
    }

    private static void listenCommands (Thread[] vendorThreads, Thread[] customerThreads) {
        System.out.println("\n========== Command Menu ==========");
        System.out.println("1. start  - Start operations");
        System.out.println("4. exit   - Exit the program");
        System.out.println("===================================");

        while (true) {

            System.out.print("[Command] Enter your choice: ");

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    if (!isRunning) {
                        isRunning = true;
                        System.out.println("[Info] Starting operations...\n");
                        startThreads(vendorThreads);
                        startThreads(customerThreads);
                    } else {
                        System.out.println("[Warning] Operations are already running.\n");
                    }
                    break;


                case "exit":
                    System.out.println("[Info] Exiting program...\n");
                    System.exit(0);

                default:
                    System.out.println("[Error] Unknown command. Please try again.\n");
            }
        }
    }

}

