package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.DiaryDTO;

import com.cbnuDiary.demo.Dto.DiaryRequestDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserChartEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserChartRepository;
import com.cbnuDiary.demo.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController

@RequestMapping("/diary")
public class DiaryController {

    private RestTemplate restTemplate;
    private  DiaryRepository diaryRepository;
    private  DiaryService diaryService;

    private  UserChartRepository userChartRepository;

    @Autowired
    public DiaryController(RestTemplate restTemplate, DiaryRepository diaryRepository,
                           DiaryService diaryService, UserChartRepository userChartRepository) {
        this.restTemplate = restTemplate;
        this.diaryRepository = diaryRepository;
        this.diaryService = diaryService;
        this.userChartRepository = userChartRepository;
    }


    private static final String MODEL_SERVER_URL = "http://150.230.251.172:5000/predict";



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
    //일기작성 컨트롤러
    @RequestMapping(value = "/write", method = RequestMethod.POST) //이 부분 수정필요
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDTO diaryDTO) {
        try {
            //여기 수정해야함 ai모델에 먼저 일기를 보내고 result_emotion값을 diary에 넣어야 db에 들어감

            diaryService.writeDiary(diaryDTO);  //우선 프론트에서 넘겨받은 작성된 일기를 저장함
            UserChartEntity userChartEntity = userChartRepository.findByUserEntity_userID(diaryDTO.getDiaryUserID())
                    .orElseThrow(() -> new RuntimeException("UserChartEntity not found"));
            DiaryRequestDTO diaryRequestDTO = new DiaryRequestDTO(diaryDTO, userChartEntity);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // HTTP 요청 본문에 DiaryDTO를 설정
            HttpEntity<DiaryRequestDTO> requestEntity = new HttpEntity<>(diaryRequestDTO, headers); //JSON으로 바꿈

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
        @RequestMapping(value = "/write2", method = RequestMethod.POST) //테스트용
        public ResponseEntity<String> write2Diary (@RequestBody DiaryDTO diaryDTO){
            System.out.println("Received request to write diary.");
            try {
                diaryService.writeDiaryTest(diaryDTO);  //우선 프론트에서 넘겨받은 작성된 일기를 저장함


                System.out.println("Diary successfully saved."); // 성공적인 저장 로그

                // 성공 응답 반환
                return ResponseEntity.ok("일기가 성공적으로 저장되었습니다.");

            } catch (Exception e) {
                e.printStackTrace(); // 에러 출력
                // 에러 발생 시 예외 처리
                return ResponseEntity.status(500).body("일기 저장 실패: " + e.getMessage());
            }


        }
    }


