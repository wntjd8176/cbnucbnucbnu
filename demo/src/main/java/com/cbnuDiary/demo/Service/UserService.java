package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void registerUser(UserDTO userDTO);
    public UserEntity convertToEntity(UserDTO userDTO);
    public UserDTO convertToDTO(UserEntity userEntity);
    public void updatePregStatus(String extractedText, String userID);
   // public boolean checkUserExist(UserDTO userDTO);
    public void updateBabyName(String userName,String newBabyName);
    public void changeID(UserDTO userDTO,String newID);
    public void changePW(String userID,String oldPW,String newPW);

    public void findID(String name, String email);

    public void findPW(String userID, String email);

    public void updateEmail(String userID,String newEmail);
    public void updateEventDay(String eventDay);
    public boolean loginApp(String userID, String userPW);

    public void withdraw(String userID,String userPW);
    public void checkDepression(UserEntity user);
    public void sendCentersToAndroid(List<CounselingCenterDTO> centers);
    public void incrementDepressCount(String userID);
    public UserDTO getUserByID(String userID);

}
