package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.UserChartDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserChartEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserChartService {
    public UserChartDTO createUserChart(UserDTO userDTO);
    public void saveUserChart(UserChartDTO userChartDTO);  //UserChartDAO이용해서 최종저장하기
    public UserChartEntity convertToEntity(UserChartDTO userChartDTO);
}
