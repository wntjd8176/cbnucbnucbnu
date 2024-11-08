package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Entity.PostEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.PostRepository;
import com.cbnuDiary.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PostDAOImpl implements PostDAO{
    private  final  PostRepository postRepository;

    @Override
    public void insertPost(PostEntity postEntity){
        postRepository.save(postEntity);
    }
    @Override
    public void deleteBypTitle(String pTitle){
        PostEntity postEntity = postRepository.findBypTitle(pTitle);
        postRepository.delete(postEntity);
    }



}
