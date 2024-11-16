package com.ticket.system.main;

import com.ticket.system.config.Config;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int totalTickets = 0;
        int maxTicketCapacity;
        int ticketReleaseRate;
        int customerRetrievalRate;


        System.out.println("Enter vendor ID: ");
        String vendorId = scanner.nextLine();

        totalTickets = getValidNumber(totalTickets, "the total number tickets");
        maxTicketCapacity = getValidNumber(totalTickets, "the maximum number tickets");
        ticketReleaseRate = getValidNumber(totalTickets, "the ticket release rate");
        customerRetrievalRate = getValidNumber(totalTickets, "the customer retrieval rate");


        Config config = new Config(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);


        try {
            TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

            config.setTicketReleaseRate(ticketReleaseRate);

            // vendor and customer threads for testing
            Thread vendor = new Thread(new Vendor(vendorId, config.getTicketReleaseRate(), 5, ticketPool));
            Thread customer = new Thread(new Customer("Customer1", config.getCustomerRetrievalRate(), ticketPool));

            vendor.start();

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

