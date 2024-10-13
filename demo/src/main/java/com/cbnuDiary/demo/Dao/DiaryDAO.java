package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;

public interface DiaryDAO {
    public boolean insert(DiaryDTO diaryDTO);
    public void update(DiaryEntity diaryEntity);
    public void deleteByDtitle(DiaryEntity diaryEntity);

}
