package com.cbnuDiary.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverMailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String recipientEmail, String userID) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Your User ID");
        message.setText("Your user ID is: " + userID);

        mailSender.send(message);
    }


}
