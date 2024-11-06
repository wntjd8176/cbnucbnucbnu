package com.cbnuDiary.demo.Service;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class ThreeNoticeService {
    public void sendDepressionAlert(String token) {
        // 알림 메시지 설정
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle("우울감 알림")
                        .setBody("우울감이 3회 감지되었습니다.")
                        .build())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
