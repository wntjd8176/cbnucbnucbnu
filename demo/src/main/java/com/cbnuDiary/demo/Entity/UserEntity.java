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
    @Id  //이 어노테이션때문에 db에 1씩올라감
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userNumberID;

    public String userID;
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserChartEntity userChartEntity;

    public String name;
    public String email;
    public boolean pregStatus;

    public String userPW;

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


    public void setPreg(){ this.pregStatus = true;
    }
}
