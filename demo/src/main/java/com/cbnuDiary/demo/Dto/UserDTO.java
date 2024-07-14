package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

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
}
