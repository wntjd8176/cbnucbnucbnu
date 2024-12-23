package com.cbnuDiary.demo.Dao;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;

public interface UserDAO {
    public boolean insert(UserEntity userEntity);
    public boolean checkUserCredentials(String userID, String userPW);
    public boolean existByUserID(String tmpUserID);
}
