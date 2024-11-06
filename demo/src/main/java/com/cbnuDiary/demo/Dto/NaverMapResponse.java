package com.cbnuDiary.demo.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class NaverMapResponse {
    private List<NaverMapItem> items;

    public List<NaverMapItem> getItems() {
        return items;
    }

    @Data
    @RequiredArgsConstructor
    public static class NaverMapItem {
        private String name;
        private String address;

        public String getAddress() {
            return address;
        }
        public String getName(){
            return name;
        }
    }

}
