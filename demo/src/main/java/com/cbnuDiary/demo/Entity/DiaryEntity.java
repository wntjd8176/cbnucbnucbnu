package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long diaryNumberID;    //일기작성시 시스템에서 자동으로 일련생성한 고유의 다이어리 ID
    public String dtitle;
    @Column(length = 500)
    public String diaryContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne(mappedBy = "diaryEntity", cascade = CascadeType.ALL)  //AnalresultEntity(주인)에 있는 diaryEntity필드에 의해서 관리된다.
    private AnalresultEntity analresultEntity;




    public DiaryEntity(){}
    public DiaryEntity(Long diaryNumberID, String Dtitle){
        this.diaryNumberID = diaryNumberID;
        this.dtitle = Dtitle;

    }
    public void setdiaryContent(String diaryContent){
        this.diaryContent = diaryContent;
    }
    public String getDiaryTitle(){return dtitle;}
}
