package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public class UserDAOImpl implements UserDAO {
    UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public boolean insert(UserEntity userEntity){

        userRepository.save(userEntity);

        return true;

    }

    @Override
    public boolean existByUserID(String tmpUserID){
        Optional<UserEntity> user = userRepository.findByuserID(tmpUserID);
        return user.isPresent(); // Optional<UserEntity>가 값이 있으면 true, 없으면 false 반환

    }
}
