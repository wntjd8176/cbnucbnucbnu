package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.PostDAO;
import com.cbnuDiary.demo.Dto.PostDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import com.cbnuDiary.demo.Entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final UserService userService;
    private final PostDAO postDAO;

    @Override
    public void writePost(UserDTO userDTO, PostDTO postDTO) {
        if (userService.checkUserExist(userDTO)) {
            PostEntity postEntity = convertToEntity(postDTO);
            postDAO.insertPost(postEntity);

        }

    }

}
