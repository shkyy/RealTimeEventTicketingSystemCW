package com.ticket.system.clients;

import com.ticket.system.main.enums.EventNames;
import com.ticket.system.main.enums.UserType;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TicketLogClient {
    public static void sendTicketLog(UserType userType, String userId, String action, EventNames eventName, BigDecimal ticketPrice) throws Exception {
        try{
            URL url = new URL("http://localhost:8080/log");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonPayload = String.format(
                    "{\"userType\":\"%s\",\"userId\":\"%s\",\"action\":\"%s\",\"eventName\":\"%s\",\"ticketPrice\":%s}",
                    userType, userId, action, eventName, ticketPrice
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input,0,input.length);
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
