package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChartDTO {

   public String userID;

    public String favoriteWord;
    public String unFavoriteWord;



    public String getUnFavoriteWord() {
        return unFavoriteWord;
    }

    public String getFavoriteWord() {
        return favoriteWord;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
