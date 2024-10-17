package com.cbnuDiary.demo.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;



@Service
public class WarningService {
    private static final String FIREBASE_SERVER_KEY = "your_server_key";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    public void sendPushNotification(String deviceToken, String title, String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(FIREBASE_SERVER_KEY);

        String notificationPayload = "{"
                + "\"to\": \"" + deviceToken + "\","
                + "\"notification\": {"
                + "\"title\": \"" + title + "\","
                + "\"body\": \"" + message + "\""
                + "}"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(notificationPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(FIREBASE_API_URL, HttpMethod.POST, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Notification sent successfully.");
        } else {
            System.out.println("Failed to send notification.");
        }
    }
}
