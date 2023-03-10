package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.HttpSessionService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.now;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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

    @MockBean
    HttpSessionService httpSessionService;


    @Test
    @DisplayName("????????? ????????? ?????? ?????? API ?????????")
    void findGasStationByName() throws Exception {
        //given
        List<FindGasStationResDto> expectedMatchingGasStations = Arrays.asList(
                FindGasStationResDto.builder().name("?????? ?????????").brand("s-oil").stationNo(1L).address("?????????").area("??????").build(),
                FindGasStationResDto.builder().name("?????? ?????? ?????????").brand("s-oil").stationNo(2L).address("?????????").area("??????").build()
        );
        given(gasStationService.getGasStationByContainingName("??????",GasType.GASOLINE))
                .willReturn(expectedMatchingGasStations);
        //when
        //then
        mockMvc.perform(get("/api/v2/gas-station")
                        .param("name", "??????")
                        .param("gasType",GasType.GASOLINE.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(DefaultCode.CHECK_MATCH_GAS_STATION.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.CHECK_MATCH_GAS_STATION.getMessage()));
    }

    @Test
    @DisplayName("????????? ???????????? ?????? api ?????? ?????????")
    void getGasStationInfoNow_SUCCESS() throws Exception {

        String name = "???????????????";
        String roadName = "???????????????";
        String buildNum = "135";
        String brand = "??????????????????";
        LocalDate date = now();
        GasStationDto gasStationDto = new GasStationDto("?????? ?????????", "???????????????", "??????????????? 135", "??????????????????", true,
                Arrays.asList(new GasDetailDto(GasType.PREMIUM_GASOLINE, 1899, date),
                        new GasDetailDto(GasType.GASOLINE, 1659, date),
                        new GasDetailDto(GasType.DIESEL, 1759, date),
                        new GasDetailDto(GasType.LPG, 0, date)));
        // "?????? ?????????", "????????????????????? ???????????????", "??????????????? 135", "??????????????????", true);
        when(gasStationService.findGasStationDto(name, roadName, buildNum, brand)).thenReturn(gasStationDto);
        mockMvc.perform(get("/api/v1/gas-station/???????????????/???????????????/135/??????????????????"))
                .andExpect(jsonPath("code").value(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL.getMessage()));

        verify(gasStationService, times(1)).findGasStationDto(name,roadName,buildNum,brand);

    }

    @Test
    @DisplayName("?????? ??? ????????? ???????????? api")
    public void testFindGasStationRecent() throws Exception {
        // Call the endpoint and expect a successful response
        mockMvc.perform(get("/api/v2/gas-station/recent")
                        .param("gasType",GasType.GASOLINE.name()))
                .andExpect(status().isOk());

    }
}