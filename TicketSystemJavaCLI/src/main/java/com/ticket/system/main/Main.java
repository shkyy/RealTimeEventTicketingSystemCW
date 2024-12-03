package com.ticket.system.main;

import com.ticket.system.config.Config;
import com.ticket.system.config.TicketConfigClient;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int totalTickets = 0;
        int maxTicketCapacity;
        int ticketReleaseRate;
        int customerRetrievalRate;

        totalTickets = getValidNumber(totalTickets, "the total number tickets");
        maxTicketCapacity = getValidNumber(totalTickets, "the maximum number tickets");
        ticketReleaseRate = getValidNumber(totalTickets, "the ticket release rate");
        customerRetrievalRate = getValidNumber(totalTickets, "the customer retrieval rate");

        TicketConfigClient.sendConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        Config config = new Config(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);


        try {
            TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

            Vendor[] vendors = new Vendor[2];
            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(config.getTicketReleaseRate(), ticketPool, config.getTotalTickets());
                Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
                vendorThread.start();
            }

            Customer[] customers = new Customer[2]; // Creating array of customers
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(config.getCustomerRetrievalRate(), ticketPool, 1);
                Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
                customerThread.start();
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
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
                System.out.println("Invalid input! Please enter an integer");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage() + " Please enter again");
            }
        }
    }

}

