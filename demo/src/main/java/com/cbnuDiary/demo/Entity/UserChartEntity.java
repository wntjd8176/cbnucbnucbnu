package com.cbnuDiary.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserChartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long chartID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private UserEntity userEntity;

    public String favoriteWord;  //3글자로 제한주기
    public String unFavoriteWord;

    public UserChartEntity(){}

    public void setFavoriteWord(String favoriteWord) {
        this.favoriteWord = favoriteWord;
    }

    public void setUnFavoriteWord(String unFavoriteWord) {
        this.unFavoriteWord = unFavoriteWord;
    }
}
