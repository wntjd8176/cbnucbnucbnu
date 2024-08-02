package com.cbnuDiary.demo.Service;

import org.springframework.stereotype.Service;

@Service
public interface CommunityService {

    public void writeObject(String userID); //안스에서 버튼을 눌러 보내준 userID 파라미터를 받는 식
    public void deleteObject(String userID);
    public void updateObject(String userID);
    public void searchObjectAKA(String userNickName);
    public void searchObjectID(String userID);
    public void searchObjectTitle(String ObTitle);
}
