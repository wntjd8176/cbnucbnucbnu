package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiaryDTO {
 public Long diaryID;
 public String dtitle;
 public String diaryContent;
 public LocalDateTime createdDate;
    public Long getDiaryID() {
        return diaryID;
    }


    public String getDiaryContent(){return diaryContent;}


    public String getDtitle(){return dtitle;}
    public LocalDateTime getCreatedDate(){return createdDate;}

    public void setCreatedDate(LocalDateTime createdDate){this.createdDate = createdDate;}

    public void setDtitle(String dtitle){this.dtitle=dtitle;}
    public void setDiaryContent(String diaryContent){this.diaryContent=diaryContent;}
}
