package com.kaspi.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaspi.backend.dto.FindGasStationReqDto;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import com.kaspi.backend.util.response.code.DefaultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserRecordController.class)
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserRecordControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    GasStationService gasStationService;


    @Test
    @DisplayName("주유소 네이밍 기준 검색 API 테스트")
    void findGasStationByName() throws Exception {
        // Given
        List<FindGasStationReqDto> expectedMatchingGasStations = Arrays.asList(
                FindGasStationReqDto.builder().name("유진 주유소").brand("s-oil").stationNo(1L).address("노원구").area("서울").build(),
                FindGasStationReqDto.builder().name("서울 유진 주유소").brand("s-oil").stationNo(2L).address("도봉구").area("서울").build()
        );
        given(gasStationService.getGasStationByContainingName("유진"))
                .willReturn(expectedMatchingGasStations);

        // When
        // Then
        mockMvc.perform(get("/api/v2/gas-station")
                        .param("name", "유진"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(DefaultCode.CHECK_MATCH_GAS_STATION.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.CHECK_MATCH_GAS_STATION.getMessage()));
    }

}