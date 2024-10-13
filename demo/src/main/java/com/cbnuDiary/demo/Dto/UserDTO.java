package com.cbnuDiary.demo.Dto;

import com.cbnuDiary.demo.Entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Setter
@Getter
public class UserDTO {
    public String name;
    public String email;
    public String userID;
    public String userPW;
    public String babyName;

    public String eventDay;

    public String getBabyName(){return babyName;}
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getUserID(){return userID;}
    public String getUserPW(){return userPW;}



    /*public static UserDTO from (UserEntity userEntity){
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(userEntity.getName());
    }*/
}
