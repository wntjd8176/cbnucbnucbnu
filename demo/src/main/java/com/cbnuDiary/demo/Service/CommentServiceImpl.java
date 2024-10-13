package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CommentDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.CommentEntity;
import com.cbnuDiary.demo.Entity.DiaryEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public void writeComment(UserDTO userDTO){

    }
    @Override
    public CommentEntity convertToEntity(CommentDTO commentDTO){
        CommentEntity commentEntity = new CommentEntity();
        return commentEntity;
    }
}
