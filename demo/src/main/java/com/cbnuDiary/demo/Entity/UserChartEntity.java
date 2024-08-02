package com.cbnuDiary.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserChartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long chartID;
    public Long userNumberID;
    public String userID;
    public String favorite


}
