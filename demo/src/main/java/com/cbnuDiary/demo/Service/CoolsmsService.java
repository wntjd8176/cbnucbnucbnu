package com.cbnuDiary.demo.Service;


import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.IOException;

import javax.annotation.PostConstruct;

@Service
public class CoolsmsService {
  /* private static final String apiKey = "";
    private static final String apiSecretKey = "";
    //private static final String API_URL = "https://api.coolsms.co.kr/messages/v4/send";

  // private DefaultMessageService messageService;



    private String phoneNumber ="01048158176";

    private final Map<String, VerificationCode> verificationCodes = new HashMap<>();
    /*@PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    } */

    public void sendSms(String to) throws Exception {
        String verificationCode = generateVerificationCode();
       // Message coolsms = new Message(apiKey, apiSecretKey);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", to);    // 수신 전화번호
       // params.put("from", phoneNumber);    // 발신 전화번호
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + verificationCode + "] 입니다.");


       // String text = "[Moyiza] 아래의 인증번호를 입력해주세요\n" + verificationCode;

        // 단일 메시지 발송
       // sendOne(to, text);
      //coolsms.send(params);
        // 인증번호 저장
        addVerificationCode(to, verificationCode);
    }


   /* private void sendOne(String to, String text) throws Exception {
        Message message = new Message();
        message.setFrom(phoneNumber); // 발신번호를 설정하세요.
        message.setTo(to);
        message.setText(text);

        try {
            SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            System.out.println("SMS 전송 성공: " + response.getMessageId());
        } catch (Exception e) {
            System.err.println("SMS 전송 실패: " + e.getMessage());
            throw e;
        }
    }*/
/*
    private String generateSignature(String apiKey, String apiSecret, String date, String salt) throws Exception {
        String message = apiKey + date + salt;
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }  */


    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 100000부터 999999까지의 랜덤 숫자
        return String.valueOf(code);
    }

    private void addVerificationCode(String phoneNumber, String code) {
        // 인증번호를 3분 동안 유효하게 설정
        //verificationCodes.put(phoneNumber, new VerificationCode(code, 3));
    }

    public boolean validateVerificationCode(String phoneNumber, String code) {
        //  VerificationCode verificationCode = verificationCodes.get(phoneNumber);
       /* if (verificationCode != null && !verificationCode.isExpired()) {
            boolean isValid = verificationCode.getCode().equals(code);
            if (isValid) {
                clearVerificationCode(phoneNumber); // 인증번호가 맞으면 삭제
            }
            return isValid;
        }
        return false; // 유효하지 않거나 만료됨
    }*/

       return false;
    }

        private void clearVerificationCode (String phoneNumber){
            //verificationCodes.remove(phoneNumber);
        }

        // VerificationCode 클래스
        private static class VerificationCode {
            private final String code;
            private final long expirationTime;

            public VerificationCode(String code, int expirationMinutes) {
                this.code = code;
                this.expirationTime = System.currentTimeMillis() + expirationMinutes * 60 * 1000;
            }

            public String getCode() {
                return code;
            }

            public boolean isExpired() {
                return System.currentTimeMillis() > expirationTime;
            }

        }
    }
