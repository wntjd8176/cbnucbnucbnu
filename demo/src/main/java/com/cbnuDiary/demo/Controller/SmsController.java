package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Service.CoolsmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {
    private final CoolsmsService coolsmsService;

    @Autowired
    public SmsController(CoolsmsService coolsmsService) {
        this.coolsmsService = coolsmsService;
    }
    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationCode(@RequestParam(name = "phoneNumber")String phoneNumber) {
        try {
            coolsmsService.sendSms(phoneNumber);
            return ResponseEntity.ok("인증번호가 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("SMS 전송에 실패했습니다.");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {
        boolean isValid = coolsmsService.validateVerificationCode(phoneNumber, code);
        if (isValid) {
            return ResponseEntity.ok("인증번호가 확인되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("유효하지 않거나 만료된 인증번호입니다.");
        }
    }

}
