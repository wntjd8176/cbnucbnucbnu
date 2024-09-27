package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import org.springframework.stereotype.Service;

@Service
public interface DiaryService {
    public DiaryEntity convertToEntity(DiaryDTO diaryDTO);
    public void deleteDiary(DiaryDTO diaryDTO);
    public void insertDiary(DiaryDTO diaryDTO);
    public void writeDiary();
    /*public void updateDiary();
    public void readDiary();

*/
}
