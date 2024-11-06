package com.cbnuDiary.demo.Dto;

import com.cbnuDiary.demo.Entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    public String name;
    public String email;
    public String userID;
    public String userPW;
    public String babyName;
    public int preg;
    public String eventDay;

    public UserDTO(){}


    public void setUserID(String userID){this.userID=userID;}

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public void setEmail(String email){this.email = email;}

    public void setName(String name) {
        this.name = name;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public void setPreg(int preg) {
        this.preg = preg;
    }

    public String getBabyName(){return babyName;}
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public String getUserID(){return userID;}
    public String getUserPW(){return userPW;}

    public int getPreg(){return preg;}



    /*public static UserDTO from (UserEntity userEntity){
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(userEntity.getName());
    }*/
}
