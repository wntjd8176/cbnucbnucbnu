package com.cbnuDiary.demo.Entity;

import com.cbnuDiary.demo.Dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

@Entity
public class UserEntity {
      //이 어노테이션때문에 db에 1씩올라감


    public Long userNumberID; //이거 1씩증가되게 할필요 있음 1103

    @Id
    @Column(name = "user_id")
    public String userID;
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserChartEntity userChartEntity;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryEntity> diaries;

    public String name;
    public String email;
    public int pregStatus=0;
    public int depressCnt;
    public String userPW;
    private String fcmToken;


    public String babyName;

    public String eventDay;

    public void setuserID(String userID){
        this.userID = userID;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setuserPW(String userPW){
        this.userPW = userPW;
    }
    public void setemail(String email){
        this.email = email;
    }
    public void setPregStatus(int pregStatus){this.pregStatus = pregStatus;}
    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public void setDepressCnt(int depressCnt){this.depressCnt = depressCnt;}

    public String getUserPW(){return userPW;}
    public String getUserID(){return userID;}
   public String getEmail(){return email;}
    public String  getName(){return name;}
    public String getBabyName() {
        return babyName;
    }

    public int getDepressCnt(){return  depressCnt;}
    public String getFcmToken() {
        return fcmToken;
    }
    public List<DiaryEntity> getDiaries() {
        return diaries;
    }
    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public void setDiaries(List<DiaryEntity> diaries) {
        this.diaries = diaries;
    }


    public int getPregStatus(){return pregStatus;}
    public void setEmail(String email){this.email = email;}


    @OneToMany(mappedBy = "userEntity")
    private List<DiaryEntity> diaryEntity = new ArrayList<>();



    public UserEntity(){}
    public UserEntity(String name, String email){
        this.name = name;
        this.email = email;
    }
    /*public static UserEntity fromDTO(UserDTO userDTO) {
        return new UserEntity(userDTO.getName(), userDTO.getEmail());
    }  이거 서비스 계층에서 하는걸로 옮김*/



}
