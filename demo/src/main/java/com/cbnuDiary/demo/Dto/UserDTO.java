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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static UserDTO from (UserEntity userEntity){
        return new UserDTO(userEntity.getName());
    }
}
