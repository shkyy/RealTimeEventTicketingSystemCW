package com.ticket.system.clients;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class TicketConfigClient {
    public static void sendConfig(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) throws Exception {
        try{
            URL url = new URL("http://localhost:8080/f");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = String.format(
                    "{\"totalTickets\":%d,\"ticketReleaseRate\":%d,\"customerRetrievalRate\":%d,\"maxTicketCapacity\":%d}",
                    totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input,0,input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                System.out.println("Configuration details sent successfully!");
            } else {
                System.out.println("Failed to send configuration details. HTTP code: "+ responseCode);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }


}
