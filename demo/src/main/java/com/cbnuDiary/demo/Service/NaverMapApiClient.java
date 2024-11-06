package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.NaverMapResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NaverMapApiClient {
    private static final String NAVER_MAP_API_URL = "https://naveropenapi.apigw.ntruss.com/map-place/v1/search";
    private static final String API_KEY = "VdoQaaWJhk65Iu4F8Fd3fcC4OJloB3rgJgskaKKm";

    private static final String client_id = "b0hegeztni";

    public List<CounselingCenterDTO> getNearbyCenters(double latitude, double longitude) {
        // 네이버 API 호출을 위한 URL과 파라미터 설정
        String url = NAVER_MAP_API_URL + "?query=상담소&coordinate=" + longitude + "," + latitude;

        // HTTP 요청 생성 및 결과 파싱 (RestTemplate 또는 WebClient 사용 가능)
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", client_id);
        headers.set("X-NCP-APIGW-API-KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverMapResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, NaverMapResponse.class);

        // 응답 결과에서 상담소 정보 추출
        return response.getBody().getItems().stream()
                .map(item -> new CounselingCenterDTO(item.getName(), item.getAddress()))
                .collect(Collectors.toList());
    }
}
