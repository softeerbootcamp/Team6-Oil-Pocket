package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import com.kaspi.backend.util.response.code.DefaultCode;
import java.time.LocalDate;
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

import static java.time.LocalDate.now;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = GasStationController.class)
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class GasStationControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    GasStationService gasStationService;


    @Test
    @DisplayName("주유소 네이밍 기준 검색 API 테스트")
    void findGasStationByName() throws Exception {
        // Given
        List<FindGasStationResDto> expectedMatchingGasStations = Arrays.asList(
                FindGasStationResDto.builder().name("유진 주유소").brand("s-oil").stationNo(1L).address("노원구").area("서울").build(),
                FindGasStationResDto.builder().name("서울 유진 주유소").brand("s-oil").stationNo(2L).address("도봉구").area("서울").build()
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

    @Test
    @DisplayName("주유소 상세정보 조회 api 성공 테스트")
    void getGasStationInfoNow_SUCCESS() throws Exception {

        String name = "평창주유소";
        String roadName = "평창문화로";
        String buildNum = "135";
        String brand = "현대오일뱅크";
        LocalDate date = now();
        GasStationDto gasStationDto = new GasStationDto("서울 종로구", "평창주유소", "평창문화로 135", "현대오일뱅크", true,
                Arrays.asList(new GasDetailDto(GasType.PREMIUM_GASOLINE, 1899, date),
                        new GasDetailDto(GasType.GASOLINE, 1659, date),
                        new GasDetailDto(GasType.DIESEL, 1759, date),
                        new GasDetailDto(GasType.LPG, 0, date)));
        // "서울 종로구", "㈜지에스이앤알 평창주유소", "평창문화로 135", "현대오일뱅크", true);
        when(gasStationService.findGasStationDto(name, roadName, buildNum, brand)).thenReturn(gasStationDto);
        mockMvc.perform(get("/api/v1/gas-station/평창주유소/평창문화로/135/현대오일뱅크"))
                .andExpect(jsonPath("code").value(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL.getMessage()));

        verify(gasStationService, times(1)).findGasStationDto(name,roadName,buildNum,brand);

    }
}