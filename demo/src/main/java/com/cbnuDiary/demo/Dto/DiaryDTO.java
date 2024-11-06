package com.cbnuDiary.demo.Dto;

import com.cbnuDiary.demo.Repository.DiaryRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiaryDTO {

    public DiaryDTO(){}

 public Long diaryID;

    public String diaryUserID;
 public String dtitle;
 public String diaryContent;
 public LocalDateTime createdDate;
  public int resultEmotion;
 public int emotions;
    /*public Long getDiaryID() {
        return diaryID;
    }*/

   public  int getResultEmotion(){return  resultEmotion;}
    public String getDiaryContent(){return diaryContent;}
    public String getDiaryUserID(){return  diaryUserID;}
    public int getEmotions(){return emotions;}

    public String getDtitle(){return dtitle;}
    public LocalDateTime getCreatedDate(){return createdDate;}


    public void setCreatedDate(LocalDateTime createdDate){this.createdDate = createdDate;}

    public void setDtitle(String dtitle){this.dtitle=dtitle;}
    public void setDiaryContent(String diaryContent){this.diaryContent=diaryContent;}

    public void setEmotions(int emotions) {
        this.emotions = emotions;
    }

    public void setResultEmotion(int resultEmotion) {
        this.resultEmotion = resultEmotion;
    }
}
