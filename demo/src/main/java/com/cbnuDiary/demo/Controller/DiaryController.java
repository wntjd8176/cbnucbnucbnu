package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final RestTemplate restTemplate;
    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;

    private static final String MODEL_SERVER_URL = "http://192.168.0.100:5000/write"; //이거 주소수정

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDiary(@RequestBody DiaryDTO diaryDTO) {
        diaryService.deleteDiary(diaryDTO);
        return ResponseEntity.ok("삭제되었습니다.");


    }

    @RequestMapping(value = "/write", method = RequestMethod.POST) //이 부분 수정필요
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDTO diaryDTO) {
        diaryService.insertDiary(diaryDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 본문에 DiaryDTO를 설정
        HttpEntity<DiaryDTO> requestEntity = new HttpEntity<>(diaryDTO, headers); //JSON으로 바꿈

        ResponseEntity<String> response = restTemplate.exchange(
                MODEL_SERVER_URL, //인공지능 서버 url
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 모델 서버 응답 반환
        return ResponseEntity.ok("모델 응답: " + response.getBody());

    } catch (Exception e) {
        // 에러 발생 시 예외 처리
        return ResponseEntity.status(500).body("모델 요청 실패: " + e.getMessage());
    }
    }

