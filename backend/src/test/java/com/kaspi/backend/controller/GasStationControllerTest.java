package com.kaspi.backend.controller;

import com.kaspi.backend.util.config.RedisConfiguration;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = GasStationController.class)
@ContextConfiguration(classes = {RedisConfiguration.class, TestRedisConfiguration.class})
class GasStationControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("주유소 상세정보 조회 테스트")
    void getGasStationDeail() throws Exception {
        /*
        GasStationDto gasStationDto = new GasStationDto( "서울 종로구", "㈜지에스이앤알 평창주유소",
                "서울 종로구 평창문화로 135 (평창동)", "현대오일뱅크", true, list);*/
        /*
        String name = URLEncoder.encode("㈜지에스이앤알 평창주유소", "utf-8");
        String roadNum = URLEncoder.encode("서울 종로구 평창문화로", "utf-8");
        String buildNum = URLEncoder.encode("135", "utf-8");
        String brand = URLEncoder.encode("현대오일뱅크", "utf-8");*/
        String name = "㈜지에스이앤알 평창주유소";
        String roadNum ="서울 종로구 평창문화로";
        String buildNum = "135";
        String brand = "현대오일뱅크";
        mvc.perform(get("/api/v1/gas-station/" + name + "/" + roadNum + "/" + buildNum + "/" + brand))
                .andExpect(jsonPath("$.name").value(name));
    }

}