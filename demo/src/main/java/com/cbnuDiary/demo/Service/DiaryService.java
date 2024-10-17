package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiaryService {
    public DiaryEntity convertToEntity(DiaryDTO diaryDTO);
    public DiaryDTO convertToDto(DiaryEntity diaryEntity);
   // public DiaryDTO getDiaryOrNull(DiaryDTO diaryDTO);
    public void deleteDiary(DiaryDTO diaryDTO);

    public void selectEmotion(Long diaryID,int Emotion);

    public void writeDiary(DiaryDTO diaryDTO);
    public void updateDiary(DiaryDTO diaryDTO);
    public DiaryEntity readDiary(DiaryDTO diaryDTO);

    public List<DiaryEntity> viewDiaryList(UserDTO userDTO);


}
