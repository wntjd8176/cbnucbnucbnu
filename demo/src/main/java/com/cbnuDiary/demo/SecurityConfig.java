package com.cbnuDiary.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/diary/write2","/sms/send","/user/register","/user/checkIDAvailability").permitAll() // 특정 경로에 대한 인증 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화 (개발 시만 사용)

        return http.build();
    }
}
