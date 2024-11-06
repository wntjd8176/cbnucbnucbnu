package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.GpsDTO;
import com.cbnuDiary.demo.Service.CounselingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/counseling")
public class CounselingController {

    private final CounselingService counselingService;


    @PostMapping("/user-location")
    public ResponseEntity<List<CounselingCenterDTO>> getCounselingCenters(@RequestBody GpsDTO gpsDTO) {
        List<CounselingCenterDTO> centers = counselingService.findNearbyCenters(gpsDTO);
        return ResponseEntity.ok(centers);
    }
}
