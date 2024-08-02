package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void registerUser(UserDTO userDTO);
}
