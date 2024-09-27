package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryDTO {
 public Long diaryID;
 public String dtitle;
 public String diaryContent;


    public Long getDiaryID() {
        return diaryID;
    }

    public String getDiaryTitle() {
        return dtitle;
    }
    public String getDiaryContent(){return diaryContent;}
}
