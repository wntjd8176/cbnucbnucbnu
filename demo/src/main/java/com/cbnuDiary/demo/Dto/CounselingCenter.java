package com.cbnuDiary.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselingCenter {
    public String name; // 상담소 이름
    public double latitude; // 위도
    public double longitude; // 경도
    public String address; // 주소

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
