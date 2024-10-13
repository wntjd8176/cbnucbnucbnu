package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.CounselingCenter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {
    private List<CounselingCenter> fetchCounselingCentersFromNaver(double latitude, double longitude) throws IOException {
        String clientId = "YOUR_CLIENT_ID"; // 네이버 API 클라이언트 ID
        String clientSecret = "YOUR_CLIENT_SECRET"; // 네이버 API 클라이언트 Secret
        String apiUrl = "https://openapi.naver.com/v1/search/local.json?query=심리상담소&display=5&start=1&sort=random";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            okhttp3.ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String jsonData = response.body().string();
                Gson gson = new Gson();
                List<CounselingCenter> centers = new ArrayList<>();

                // JSON 파싱
                JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("items");
                for (int i = 0; i < items.size(); i++) {
                    JsonObject item = items.get(i).getAsJsonObject();
                    CounselingCenter center = new CounselingCenter();
                    center.setName(item.get("title").getAsString());
                    center.setLatitude(item.get("mapx").getAsDouble());
                    center.setLongitude(item.get("mapy").getAsDouble());
                    center.setAddress(item.get("address").getAsString());
                    centers.add(center);
                }
                return centers;
            }
        }
        return new ArrayList<>();
    }
    // 사용자 요청 처리 메서드
    @GetMapping("/counseling-centers")
    public List<CounselingCenter> getNearbyCounselingCenters(@RequestParam double latitude, @RequestParam double longitude) {
        try {
            return fetchCounselingCentersFromNaver(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // 에러 발생 시 빈 리스트 반환
        }
    }

}
