package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public class DiaryDAOImpl implements DiaryDAO {
    DiaryRepository diaryRepository;

    @Autowired
    public DiaryDAOImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override
    public boolean insert(DiaryDTO diaryDto) {
        DiaryEntity diaryEntity = new DiaryEntity(diaryDto.getDiaryID(), diaryDto.getDtitle());
        diaryRepository.save(diaryEntity);

        return true;

    }
    @Override
    public void update(DiaryEntity diaryEntity){
        diaryRepository.save(diaryEntity);
    }
   @Override
   public void deleteByDtitle(DiaryEntity diaryEntity){
        diaryRepository.deleteBydtitle(diaryEntity.getDtitle());

   }


}
