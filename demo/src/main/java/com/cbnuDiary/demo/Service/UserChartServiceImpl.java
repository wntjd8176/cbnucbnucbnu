package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.UserChartDAO;
import com.cbnuDiary.demo.Dto.UserChartDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserChartEntity;
import com.cbnuDiary.demo.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class UserChartServiceImpl implements UserChartService{
    UserChartDAO userChartDAO;
    @Override
    public UserChartDTO createUserChart(UserDTO userDTO){
     UserChartDTO userChartDTO = new UserChartDTO();
     userChartDTO.setUserID(userDTO.getUserID());
     return userChartDTO;
   //userDTO에서 userID가져와서 chart에 넣어서 생성한다
    }
    @Override
    public void saveUserChart(UserChartDTO userChartDTO){
       UserChartEntity userChartEntity = convertToEntity(userChartDTO);
       userChartDAO.insert(userChartEntity);
    }

    @Override
    public UserChartEntity convertToEntity(UserChartDTO userChartDTO){

        UserChartEntity userChartEntity = new UserChartEntity();
        userChartEntity.setFavoriteWord(userChartDTO.getFavoriteWord());
        userChartEntity.setUnFavoriteWord(userChartDTO.getUnFavoriteWord());



        return userChartEntity;
    }
}
