package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void registerUser(UserDTO userDTO);
    public UserEntity convertToEntity(UserDTO userDTO);
    public void updatePregStatus();
    public boolean checkUserExist(UserDTO userDTO);
}
