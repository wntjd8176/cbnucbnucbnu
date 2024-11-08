package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.DiaryDAO;
import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService{


    private final DiaryRepository diaryRepository;
   // private  final DiaryDTO diaryDTO;
   private  final UserRepository userRepository;
    private final ThreeNoticeService threeNoticeService;
    private final DiaryDAO diaryDAO;
    /*@Autowired  RequiredArgsConstructor가 생성자 주입해준다.
    public DiaryServiceImpl(DiaryDAO diaryDAO) {
        this.diaryDAO = diaryDAO;
    }*/

    @Override
    public DiaryEntity convertToEntity(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setdiaryContent(diaryDTO.getDiaryContent());
        diaryEntity.setDtitle(diaryDTO.getDtitle());

        UserEntity userEntity = userRepository.findByuserID(diaryDTO.getDiaryUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + diaryDTO.getDiaryUserID()));
        diaryEntity.setUserEntity(userEntity);
        diaryEntity.setdiaryUserID(userEntity.getUserID());
        return diaryEntity;
    }
    @Override
    public DiaryEntity convertToEntityTest(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setdiaryContent(diaryDTO.getDiaryContent());
        diaryEntity.setDtitle(diaryDTO.getDtitle());


        diaryEntity.setdiaryUserID(diaryDTO.getDiaryUserID());
        diaryEntity.setCreatedDate(diaryDTO.getCreatedDate());
        diaryEntity.setEmotions(diaryDTO.getEmotions());
        diaryEntity.setResultEmotion(diaryDTO.getResultEmotion());

        return diaryEntity;
    }
    @Override
    public DiaryDTO convertToDto(DiaryEntity diaryEntity) {
        // DiaryEntity -> DiaryDTO 변환 로직
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDtitle(diaryEntity.getDtitle());
        diaryDTO.setDiaryContent(diaryEntity.getDiaryContent());
        // 기타 필요한 필드 변환
        return diaryDTO;
    }

    @Override
    public void selectEmotion(Long diaryID,int emotion){
        DiaryEntity diaryEntity = diaryRepository.findBydiaryID(diaryID)
                .orElseThrow(() -> new IllegalArgumentException("해당 다이어리가 존재하지 않습니다: " + diaryID));


        diaryEntity.setEmotions(emotion);


        diaryRepository.save(diaryEntity);
    }


  @Override
  public void setResultEmotion(String userID,int resultEmotion){

  }


    @Override
    public void deleteDiary(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = convertToEntity(diaryDTO);
        diaryDAO.deleteByDtitle(diaryEntity);
    }

    @Override
    public void writeDiary(DiaryDTO diaryDTO) {

        if (diaryDTO.getDtitle() == null || diaryDTO.getDtitle().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }

            diaryDTO.setCreatedDate(LocalDateTime.now());



            diaryDAO.insert(diaryDTO);

    }

    @Transactional
    @Override
    public void writeDiaryTest(DiaryDTO diaryDTO) {

        if (diaryDTO.getDtitle() == null || diaryDTO.getDtitle().isEmpty()) {
            throw new IllegalArgumentException("제목은 비어 있을 수 없습니다.");
        }
        UserEntity userEntity = userRepository.findByuserID(diaryDTO.getDiaryUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        diaryDTO.setCreatedDate(LocalDateTime.now());

        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setdiaryContent(diaryDTO.getDiaryContent());
        diaryEntity.setDtitle(diaryDTO.getDtitle());


        diaryEntity.setdiaryUserID(diaryDTO.getDiaryUserID());
        diaryEntity.setCreatedDate(diaryDTO.getCreatedDate());
        diaryEntity.setEmotions(diaryDTO.getEmotions());
        diaryEntity.setResultEmotion(diaryDTO.getResultEmotion());

        diaryRepository.save(diaryEntity);

        if (userEntity.getDepressCnt() == 3) {
            threeNoticeService.sendDepressionAlert(userEntity.getFcmToken());
        }
        //diaryDAO.insertTest(diaryDTO);

    }
    @Override
    public void updateDiary(DiaryDTO diaryDTO){
        DiaryEntity existDiary = diaryRepository.findBydtitle(diaryDTO.getDtitle());


        if (existDiary == null) {
            throw new IllegalArgumentException("해당 제목의 일기를 찾을 수 없습니다.");
        }

        if (diaryDTO.getDtitle() != null && !diaryDTO.getDtitle().isEmpty()) {
            existDiary.setDtitle(diaryDTO.getDtitle());
        }
        if (diaryDTO.getDiaryContent() != null && !diaryDTO.getDiaryContent().isEmpty()) {
            existDiary.setdiaryContent(diaryDTO.getDiaryContent());
        }

        diaryDTO.setCreatedDate(LocalDateTime.now());


        diaryDAO.update(existDiary);
    }
    /*
    @Override    //이거랑 밑에 read부분은 차후에 일기객체를 선택할시,보여주는 메소드로 쓸 수 있을듯
    public DiaryDTO getDiaryOrNull(DiaryDTO diaryDTO) {
        // Optional 처리
        Optional<DiaryEntity> diaryEntityOpt = readDiary(diaryDTO);

        // 값이 존재하면 DiaryDTO로 변환하여 반환, 없으면 null 반환
        return diaryEntityOpt
                .map(this::convertToDto) // DiaryEntity -> DiaryDTO 변환 메소드
                .orElse(null);
    } */
    @Override
    public DiaryEntity readDiary(DiaryDTO diaryDTO){
        String diaryTitle = diaryDTO.getDtitle();

        DiaryEntity showDiary = diaryRepository.findBydtitle(diaryTitle);

        return  showDiary;


    }
   @Override
    public List<DiaryEntity> viewDiaryList(UserDTO userDTO){

       String userID = userDTO.getUserID();

       return diaryRepository.findByUserEntity_userID(userID);
   }








}
