package com.cbnuDiary.demo.Dto;

import com.cbnuDiary.demo.Entity.UserChartEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DiaryRequestDTO {
    private DiaryDTO diaryDTO;
    private UserChartEntity userChartEntity;

    public DiaryRequestDTO(){}

    public DiaryRequestDTO(DiaryDTO diaryDTO , UserChartEntity userChartEntity){
        this.diaryDTO = diaryDTO;
        this.userChartEntity = userChartEntity;
    }

    public DiaryDTO getDiaryDTO() {
        return diaryDTO;
    }

    public void setDiaryDTO(DiaryDTO diaryDTO) {
        this.diaryDTO = diaryDTO;
    }

    public UserChartEntity getUserChartEntity() {
        return userChartEntity;
    }

    public void setUserChartEntity(UserChartEntity userChartEntity) {
        this.userChartEntity = userChartEntity;
    }


}
