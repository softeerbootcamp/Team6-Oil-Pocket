package com.kaspi.backend.util.opinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class OpinetRequest {
    private static final String OPINET_API_KEY = "F230130021";

    public static JSONObject requestNowAverage() throws IOException, JSONException {
        URL url = new URL("http://www.opinet.co.kr/api/avgAllPrice.do?out=json&code=" + OPINET_API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        log.info("오피넷 전국 유가 평균 가격 조회 response:{}", con.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());

    }
}
