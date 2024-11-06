package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.GpsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CounselingService {
    public List<CounselingCenterDTO> findNearbyCenters(GpsDTO gpsDTO);

}
