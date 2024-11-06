package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryID")
    public Long diaryID;    //일기작성시 시스템에서 자동으로 일련생성한 고유의 다이어리 ID
    public String dtitle;

    public String diaryUserID;
    public int emotions;  //1.좋음 2.슬픔 3.화남 4.우울 5.무감정
    @Column(length = 500)
    public String diaryContent;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @OneToOne(mappedBy = "diaryEntity", cascade = CascadeType.ALL)  //AnalresultEntity(주인)에 있는 diaryEntity필드에 의해서 관리된다.
    private AnalresultEntity analresultEntity;

    public int resultEmotion ;



    public DiaryEntity(){}
    public DiaryEntity(Long diaryID, String Dtitle){
        this.diaryID = diaryID;
        this.dtitle = Dtitle;

    }
    public void setUserEntity(UserEntity userEntity) {
       this.userEntity = userEntity;
    }
    public void setdiaryUserID(String diaryUserID){
        this.diaryUserID =diaryUserID;
    }
    public void setdiaryContent(String diaryContent){
        this.diaryContent = diaryContent;
    }
    public void setResultEmotion(int resultEmotion) {this.resultEmotion = resultEmotion;}
   public void setDtitle(String dtitle){this.dtitle=dtitle;}
    public void setCreatedDate(LocalDateTime createdDate){this.createdDate=createdDate;}

    public void setEmotions(int emotion){this.emotions = emotion;}

    public String getDtitle(){return dtitle;}
    public String getDiaryContent(){return diaryContent;}


}
