package com.cbnuDiary.demo.Repository;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {
}
