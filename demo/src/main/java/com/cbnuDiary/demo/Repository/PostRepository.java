package com.cbnuDiary.demo.Repository;

import com.cbnuDiary.demo.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
