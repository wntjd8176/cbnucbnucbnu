package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   private final UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public void registerUser(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.fromDTO(userDTO);
        userDAO.insert(userEntity);
    }
}
