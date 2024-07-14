package com.cbnuDiary.demo.Repository;

import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
