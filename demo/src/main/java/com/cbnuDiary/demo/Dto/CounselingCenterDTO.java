package com.cbnuDiary.demo.Dto;

import lombok.*;

@Data

@Getter
@Setter

public class CounselingCenterDTO {
    private String name;
    private String address;
   public CounselingCenterDTO(){}
    public CounselingCenterDTO(String name, String address){
       this.name = name;
       this.address =address;
    }
    public String getAddress() {
        return address;
    }
    public String getName(){
        return name;
    }
}
