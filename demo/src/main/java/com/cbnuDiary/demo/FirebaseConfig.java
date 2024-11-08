package com.cbnuDiary.demo;

import org.springframework.context.annotation.Configuration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp initializeFirebase() throws IOException {

        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");

        if (serviceAccount == null) {
            throw new FileNotFoundException("firebase-service-account.json 파일을 찾을 수 없습니다.");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirebaseApp.getInstance();
    }

}
