package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.DiaryDAO;
import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService{


    private final DiaryRepository diaryRepository;

    private final DiaryDAO diaryDAO;
    /*@Autowired  RequiredArgsConstructor가 생성자 주입해준다.
    public DiaryServiceImpl(DiaryDAO diaryDAO) {
        this.diaryDAO = diaryDAO;
    }*/

    @Override
    public DiaryEntity convertToEntity(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setdiaryContent(diaryDTO.getDiaryContent());
       /* diaryEntity.setdtitle(diaryDTO.getName());
        diaryEntity.setemail(diaryDTO.getEmail());
        diaryEntity.setuserPW(diaryDTO.getUserPW());*/
        return diaryEntity;
    }
    @Override
    public void deleteDiary(DiaryDTO diaryDTO){
        DiaryEntity diaryEntity = convertToEntity(diaryDTO);
        diaryDAO.deleteByDtitle(diaryEntity);
    }
    @Override
    public void insertDiary(DiaryDTO diaryDTO){

    }
}
