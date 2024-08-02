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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long userNumberID;
    public String userID;
    public String name;
    public String email;



    @OneToMany(mappedBy = "userEntity")
    private List<DiaryEntity> diaryEntity = new ArrayList<>();
    public UserEntity(String name, String email){
        this.name = name;
        this.email = email;
    }
    public static UserEntity fromDTO(UserDTO userDTO) {
        return new UserEntity(userDTO.getName(), userDTO.getEmail());
    }
}
