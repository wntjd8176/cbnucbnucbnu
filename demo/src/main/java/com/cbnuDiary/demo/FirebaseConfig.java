package com.cbnuDiary.demo;

import org.springframework.context.annotation.Configuration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
/*
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("path/to/serviceAccountKey.json");  // 이 부분 수정필요

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }
*/
}
