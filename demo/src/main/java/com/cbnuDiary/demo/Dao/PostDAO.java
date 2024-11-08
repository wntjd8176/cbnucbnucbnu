package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Entity.PostEntity;

public interface PostDAO {
    public void insertPost(PostEntity postEntity);
    public void deleteBypTitle(String pTitle);
}
