package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CommentDTO;
import com.cbnuDiary.demo.Dto.PostDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.CommentEntity;
import com.cbnuDiary.demo.Entity.PostEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentEntity convertToEntity(CommentDTO commentDTO);
    public void writeComment(UserDTO userDTO);
   /* public void deleteComment(String nickName,String cTitle);
    public void serachComment();
    public void readComment();*/
}
