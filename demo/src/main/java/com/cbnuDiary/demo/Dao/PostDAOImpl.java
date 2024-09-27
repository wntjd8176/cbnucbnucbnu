package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Entity.PostEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostDAOImpl implements PostDAO{
    PostRepository postRepository;

    @Autowired
    public PostDAOImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public void insertPost(PostEntity postEntity){
        postRepository.save(postEntity);
    }
}
