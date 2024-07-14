package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long diaryID;
    public String diaryTitle;
    @Column(length = 500)
    public String diaryContent;
    public DiaryEntity(Long diaryID, String diaryTitle){
        this.diaryID = diaryID;
        this.diaryTitle = diaryTitle;

    }
}
