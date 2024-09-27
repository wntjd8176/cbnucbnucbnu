package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.PostDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.PostEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    public PostEntity convertToEntity(PostDTO postDTO);
    public void writePost(UserDTO userDTO, PostDTO postDTO);
}
