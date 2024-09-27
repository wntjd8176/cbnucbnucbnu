package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;

@Entity
public class AnalresultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long analResultID;

    public String resultContent;

    @OneToOne
    @JoinColumn(name = "diary_Number_ID")
    private DiaryEntity diaryEntity;




}
