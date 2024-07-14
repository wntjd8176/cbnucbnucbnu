package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public boolean insert(UserDTO userDto){
        UserEntity userEntity = new UserEntity(userDto.getName(),userDto.getEmail());
        userRepository.save(userEntity);

        return true;


    }
}
