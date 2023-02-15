package com.kaspi.backend.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.dto.UserGasRecordResDto;
import com.kaspi.backend.enums.GasBrand;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.OpinetService;
import com.kaspi.backend.service.UserRecordService;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = UserGasRecordController.class)
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserGasRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GasStationService gasStationService;

    @MockBean
    UserRecordService userRecordService;

    @MockBean
    OpinetService opinetService;


    @Test
    @DisplayName("사용자 주유 기록 입력 API 테스트")
    void postUserGasRecord() throws Exception {
        Long userRefuelingPrice = 15000L;
        Long userGasAmount = 10L;
        Long nationalGasAvg = 1000L;
        Long userSavingAmount = 5000L;
        UserGasRecordReqDto userGasRecordReqDto = UserGasRecordReqDto.builder()
                .gasStationNo(1L)
                .gasType(GasType.GASOLINE)
                .refuelingPrice(userRefuelingPrice).build();

        GasStation gasStation = GasStation.builder()
                .stationNo(1L)
                .build();

        when(gasStationService.getGasStationByNo(userGasRecordReqDto.getGasStationNo())).thenReturn(gasStation);
        when(userRecordService.calTodayUserGasAmount(userGasRecordReqDto, gasStation))
                .thenReturn(userGasAmount);// 유저가 총 넣은 가스 리터 수
        when(opinetService.nationalAvgOilPrice(userGasRecordReqDto.getGasType())).thenReturn(
                nationalGasAvg);//전국 리터당 평균 가격
        when(userRecordService.calUserSavingAmount(userGasRecordReqDto.getRefuelingPrice(),
                userGasAmount, nationalGasAvg))
                .thenReturn(userSavingAmount);//유저가 절약한 금액

        mockMvc.perform(post("/api/v2/user/gas-record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(userGasRecordReqDto)))
                .andExpect(status().isCreated());
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserGasRecord() throws Exception {
        //given
        List<UserGasRecordResDto> userGasRecords = makeUserGasRecordDto();
        when(userRecordService.getUserRecords()).thenReturn(userGasRecords);
        //when//then
        mockMvc.perform(get("/api/v2/user/gas-record")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].chargeDate").value(userGasRecords.get(0).getChargeDate().toString()))
                .andExpect(jsonPath("$.data[1].chargeDate").value((userGasRecords.get(1).getChargeDate().toString())));
    }

    private List<UserGasRecordResDto> makeUserGasRecordDto() {
        List<UserGasRecordResDto> userGasRecords = new ArrayList<>();
        UserGasRecordResDto gasRecordDto = UserGasRecordResDto.builder()
                .chargeDate(LocalDate.now().plusMonths(1))
                .recordGasAmount("10L")
                .brand(GasBrand.getImgByDbName(GasBrand.SK_GAS.getDbName()))
                .gasStationName("유진주유소")
                .gasType(GasType.DIESEL.name())
                .savingPrice("100원")
                .refuelingPrice("10000원").build();
        UserGasRecordResDto gasRecordDto2 = UserGasRecordResDto.builder()
                .chargeDate(LocalDate.now())
                .recordGasAmount("20L")
                .brand(GasBrand.getImgByDbName(GasBrand.SK_GAS.getDbName()))
                .gasStationName("유진2주유소")
                .gasType(GasType.DIESEL.name())
                .savingPrice("200원")
                .refuelingPrice("20000원").build();
        userGasRecords.add(gasRecordDto);
        userGasRecords.add(gasRecordDto2);
        return userGasRecords;
    }
}