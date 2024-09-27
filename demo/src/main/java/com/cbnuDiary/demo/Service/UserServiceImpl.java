package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Exception.userIDAlreadyExistsException;
import com.cbnuDiary.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


   private final UserRepository userRepository;

   private final UserDAO userDAO;
    /*@Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }*/
    @Override
    public UserEntity convertToEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();   //0910날 함 이부분 수정해야함
        userEntity.setuserID(userDTO.getUserID());
        userEntity.setname(userDTO.getName());
        userEntity.setemail(userDTO.getEmail());
        userEntity.setuserPW(userDTO.getUserPW());
        return userEntity;
    }
    @Override
    public void registerUser(UserDTO userDTO) {
        if(userDAO.existByUserID(userDTO.getUserID())){
            throw new userIDAlreadyExistsException("Username already exists: " + userDTO.getUserID());
        }
        UserEntity userEntity = convertToEntity(userDTO);
        userDAO.insert(userEntity);
    }

    @Override
    public void updatePregStatus(){  //이 부분 수정필요 아예 다시생각해야함
        UserEntity user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPreg();
        userRepository.save(user);
    }



    @Override
    public boolean checkUserExist(UserDTO userDTO){
        String userID = userDTO.getUserID();
        return userRepository.existsByuserId(userID);
    }
    }

