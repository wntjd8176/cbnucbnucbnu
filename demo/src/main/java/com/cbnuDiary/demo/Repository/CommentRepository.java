package com.cbnuDiary.demo.Repository;

import com.cbnuDiary.demo.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
