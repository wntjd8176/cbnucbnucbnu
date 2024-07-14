package com.cbnuDiary.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long userID;
    public String name;
    public String email;


    public UserEntity(String name, String email){
        this.name = name;
        this.email = email;
    }

}
