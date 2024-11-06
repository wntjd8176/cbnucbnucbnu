package com.cbnuDiary.demo.Repository;

import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.UserChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserChartRepository extends JpaRepository<UserChartEntity,Long> {
    Optional<UserChartEntity> findByUserEntity_userID(String userID);


}
