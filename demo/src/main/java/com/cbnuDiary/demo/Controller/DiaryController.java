package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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

    @RequestMapping(value ="/titleread",method = RequestMethod.GET)
    public ResponseEntity<DiaryDTO> readDiary(@RequestParam String diarytitle) {
        //제목에 맞는 diaryEntity를 가져온다음 그걸 다시 dto로 바꿔준다.
        DiaryEntity diaryEntity = diaryRepository.findBydtitle(diarytitle);

        // DiaryEntity가 존재하는지 확인하고 DTO로 변환
        if (diaryEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // getDiaryOrNull 호출
        DiaryDTO result = diaryService.convertToDto(diaryEntity);


        // 일기가 없으면 404 Not Found 응답 반환
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // 일기가 있으면 200 OK와 함께 DTO 반환
        return ResponseEntity.ok(result);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateDiary(@PathVariable Long id, @RequestBody DiaryDTO updatedDiaryDTO){
        try {
            diaryService.updateDiary(updatedDiaryDTO);
            return ResponseEntity.ok("수정완료");
        }catch (Exception e){
            return ResponseEntity.status(500).body("실패: " + e.getMessage());
        }
       /* Optional<DiaryEntity> existingDiary = diaryRepository.findById(id);

        // 일기 존재 여부 확인
        if (existingDiary.isPresent()) {
            DiaryEntity diaryEntity = existingDiary.get();  //여기선 DTO말고 Entity써야함

            // DiaryEntity의 필드를 updatedDiaryDTO의 값으로 업데이트
            diaryEntity.setDtitle(updatedDiaryDTO.getDtitle());
            diaryEntity.setdiaryContent(updatedDiaryDTO.getDiaryContent());
            diaryEntity.setCreatedDate(updatedDiaryDTO.getCreatedDate());

            // 수정된 일기 저장
            diaryRepository.save(diaryEntity);

            return ResponseEntity.ok("Diary updated successfully.");
        } else {
            // 일기를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diary not found.");
        }*/
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST) //이 부분 수정필요
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDTO diaryDTO) {
        try {
            diaryService.writeDiary(diaryDTO);

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
}

