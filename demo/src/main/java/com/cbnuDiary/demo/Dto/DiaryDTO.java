package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryDTO {
 private Long diaryID;
 private String diaryTitle;

    public Long getDiaryID() {
        return diaryID;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

}
