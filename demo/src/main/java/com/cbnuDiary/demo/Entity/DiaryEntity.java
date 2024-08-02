package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long diaryNumberID;    //일기작성시 시스템에서 자동으로 일련생성한 고유의 다이어리 ID
    public String diaryTitle;
    @Column(length = 500)
    public String diaryContent;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public DiaryEntity(Long diaryID, String diaryTitle){
        this.diaryID = diaryID;
        this.diaryTitle = diaryTitle;

    }
}
