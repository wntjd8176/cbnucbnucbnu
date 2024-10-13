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
    public Long diaryID;    //일기작성시 시스템에서 자동으로 일련생성한 고유의 다이어리 ID
    public String dtitle;
    public int emotions;
    @Column(length = 500)
    public String diaryContent;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne(mappedBy = "diaryEntity", cascade = CascadeType.ALL)  //AnalresultEntity(주인)에 있는 diaryEntity필드에 의해서 관리된다.
    private AnalresultEntity analresultEntity;




    public DiaryEntity(){}
    public DiaryEntity(Long diaryID, String Dtitle){
        this.diaryID = diaryID;
        this.dtitle = Dtitle;

    }
    public void setdiaryContent(String diaryContent){
        this.diaryContent = diaryContent;
    }

   public void setDtitle(String dtitle){this.dtitle=dtitle;}
    public void setCreatedDate(LocalDateTime createdDate){this.createdDate=createdDate;}

    public void setEmotions(int emotion){this.emotion = emotion;}

    public String getDtitle(){return dtitle;}
    public String getDiaryContent(){return diaryContent;}


}
