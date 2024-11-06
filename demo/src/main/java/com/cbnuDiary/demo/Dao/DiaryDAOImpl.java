package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserRepository;
import com.cbnuDiary.demo.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public class DiaryDAOImpl implements DiaryDAO {
    DiaryRepository diaryRepository;
   // DiaryService diaryService;

    @Autowired
    public DiaryDAOImpl(DiaryRepository diaryRepository ) {
        this.diaryRepository = diaryRepository;

    }

    @Override
    public boolean insert(DiaryDTO diaryDto) {
      // DiaryEntity diaryEntity = diaryService.convertToEntity(diaryDto);   //1103

       // diaryRepository.save(diaryDto);

       return true;

    }
    @Override
    public boolean insertTest(DiaryDTO diaryDto) {

      //  DiaryEntity diaryEntity = diaryService.convertToEntityTest(diaryDto);
      //  diaryRepository.save(diaryDto);

      //  System.out.println("Diary saved: " + diaryDto);
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
