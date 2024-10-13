package com.cbnuDiary.demo.Repository;
import com.cbnuDiary.demo.Dto.DiaryDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {
    void deleteBydtitle(String dtitle);
    DiaryEntity findBydtitle(String dtitle);
    List<DiaryEntity> findByUserEntity_userID(String userID);


    Optional<DiaryEntity> findBydiaryID(Long diaryID);
}
