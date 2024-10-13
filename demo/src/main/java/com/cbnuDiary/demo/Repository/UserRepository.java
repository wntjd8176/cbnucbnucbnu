package com.cbnuDiary.demo.Repository;

import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByuserID(String userID);
    boolean existsByuserID(String userID);

    Optional<UserEntity>findByuserPW(String userPW);
    Optional<UserEntity> findByname(String userName);

    Optional<UserEntity>findBynameAndemail(String name, String email);
}
