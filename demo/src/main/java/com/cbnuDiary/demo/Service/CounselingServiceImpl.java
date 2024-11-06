package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.GpsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CounselingServiceImpl implements CounselingService{

    private final NaverMapApiClient naverMapApiClient;
    @Override
    public List<CounselingCenterDTO> findNearbyCenters(GpsDTO gpsDTO){
        // Naver Map API를 호출하여 GPS 위치로부터 근처 상담소 정보를 가져옴
        return naverMapApiClient.getNearbyCenters(gpsDTO.getLatitude(), gpsDTO.getLongitude());
    }



}
