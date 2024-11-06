package com.cbnuDiary.demo.Dao;

import com.cbnuDiary.demo.Dto.UserChartDTO;
import com.cbnuDiary.demo.Entity.UserChartEntity;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserChartRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserChartDAOImpl implements UserChartDAO{
    UserChartRepository userChartRepository;
    @Autowired
    public UserChartDAOImpl(UserChartRepository userChartRepository) {
        this.userChartRepository = userChartRepository;
    }
    @Override
    public void insert(UserChartEntity userChartEntity){

     userChartRepository.save(userChartEntity);

    }
}
