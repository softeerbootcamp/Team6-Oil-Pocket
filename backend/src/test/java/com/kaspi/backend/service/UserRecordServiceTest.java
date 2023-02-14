package com.kaspi.backend.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kaspi.backend.dao.FoodImageDao;
import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.UserGasRecordDao;
import com.kaspi.backend.domain.*;
import com.kaspi.backend.dto.UserEcoRecordResDto;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.GasType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import com.kaspi.backend.enums.Gender;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRecordServiceTest {
    @InjectMocks
    private UserRecordService userRecordService;

    @Mock
    private GasDetailDao gasDetailDao;
    @Mock
    private UserGasRecordDao userGasRecordDao;
    @Mock
    private FoodImageDao foodImageDao;
    @Mock
    private HttpSessionService httpSessionService;

    @Test
    @DisplayName("사용자입력정보로 부터 얼마만큼 충전(L)했는지 테스트")
    void calTodayUserGasAmount() {
        //given
        Long userRefuelMoney = 5000L;
        Long gasolinePerLiterPrice = 1000L;

        UserGasRecordReqDto userGasRecordReqDto = UserGasRecordReqDto.builder().gasType(GasType.GASOLINE)
                .gasStationNo(1L)
                .refuelingPrice(userRefuelMoney).build();
        GasStation gasStation = GasStation.builder()
                .stationNo(1L).build();
        when(gasDetailDao.findTodayGasPrice(gasStation.getStationNo(),
                userGasRecordReqDto.getGasType().name(),
                GasDetail.getNowDateToStr())) // 항상 오늘날짜로 기준
                .thenReturn(Optional.of(gasolinePerLiterPrice));//가솔린 1L당 1000원
        //when
        Long userGasAmount = userRecordService.calTodayUserGasAmount(userGasRecordReqDto, gasStation);
        //then
        assertEquals(5L, userGasAmount);
    }

    @Test
    @DisplayName("사용자입력정보로 부터 얻은 값이 없는 경우 테스트")
    void calTodayUserGasAmount_Exception() {
        //given
        Long userRefuelMoney = 5000L;
        Long gasolinePerLiterPrice = 1000L;

        UserGasRecordReqDto userGasRecordReqDto = UserGasRecordReqDto.builder().gasType(GasType.GASOLINE)
                .gasStationNo(1L)
                .refuelingPrice(userRefuelMoney).build();
        GasStation gasStation = GasStation.builder()
                .stationNo(1L).build();
        when(gasDetailDao.findTodayGasPrice(gasStation.getStationNo(),
                userGasRecordReqDto.getGasType().name(),
                GasDetail.getNowDateToStr())) // 항상 오늘날짜로 기준
                .thenReturn(Optional.empty());//가솔린 1L당 1000원
        //when
        assertThrows(NoSuchElementException.class, () -> {
            userRecordService.calTodayUserGasAmount(userGasRecordReqDto, gasStation);
        });


    }

    @Test
    @DisplayName("사용자가 이익/손해 로직 계산")
    void calUserSavingAmount() {
        //given
        Long userGasAmount = 2L;
        Long nationalGasAvg = 1200L;
        Long userGasRefuelingPrice = 2400L;
        Long actualSavingAmount = userRecordService.calUserSavingAmount(userGasRefuelingPrice, userGasAmount,
                nationalGasAvg);
        Assertions.assertThat(0L).isEqualTo(actualSavingAmount);
    }

    @Test
    @DisplayName("사용자 주유기록 저장")
    void saveUserGasRecord() {
        //given
        UserGasRecordReqDto userGasRecordReqDto = UserGasRecordReqDto.builder()
                .refuelingPrice(1000L)
                .gasType(GasType.GASOLINE)
                .build();

        GasStation gasStation = GasStation.builder()
                .stationNo(1L)
                .build();
        User user = User.builder()
                .userNo(1L)
                .build();
        when(httpSessionService.getUserFromSession()).thenReturn(user);
        //when
        userRecordService.saveUserGasRecord(userGasRecordReqDto, gasStation, 1000L, 900L);

        verify(httpSessionService, times(1)).getUserFromSession();
    }

    @Test
    @DisplayName("주유기록 저장 테스트")
    void testSaveUserGasRecord() {
        // Given
        UserGasRecordReqDto userGasRecordReqDto = UserGasRecordReqDto.builder()
                .refuelingPrice(10000L)
                .gasType(GasType.GASOLINE)
                .build();

        GasStation gasStation = GasStation.builder()
                .stationNo(1L)
                .build();

        User user = User.builder()
                .userNo(1L)
                .build();

        UserGasRecord userGasRecord = UserGasRecord.builder()
                .userNo(user.getUserNo())
                .gasStationNo(gasStation.getStationNo())
                .chargeDate(new Date())
                .refuelingPrice(userGasRecordReqDto.getRefuelingPrice())
                .recordGasAmount(10L)
                .savingPrice(1000L)
                .recordGasType(userGasRecordReqDto.getGasType())
                .build();

        when(httpSessionService.getUserFromSession()).thenReturn(user);
        when(userGasRecordDao.save(any(UserGasRecord.class))).thenReturn(userGasRecord);

        // When
        UserGasRecord result = userRecordService.saveUserGasRecord(userGasRecordReqDto, gasStation, 10L, 1000L);

        // Then
        verify(userGasRecordDao, times(1)).save(any(UserGasRecord.class));
        assertThat(result).isEqualTo(userGasRecord);
    }
    @Test
    @DisplayName("절약 정보 조회 테스트")
    public void calMonthUserEcoPrice_ShouldReturnUserEcoRecordResDto() {
        User user = new User(1L, "user1", "password", Gender.MALE, Age.FORTY);
        LocalDate date = LocalDate.now();
        List<EcoRecord> rankSavingPrices = Arrays.asList(
                EcoRecord.builder().userNo(2L).gender(Gender.MALE).age(Age.FORTY).refuelingPrice(50000).savingPrice(400).perRank(0).build(),
                EcoRecord.builder().userNo(3L).gender(Gender.MALE).age(Age.FORTY).refuelingPrice(20000).savingPrice(360).perRank(0.5).build(),
                EcoRecord.builder().userNo(user.getUserNo()).gender(Gender.MALE).age(Age.FORTY).refuelingPrice(20000).savingPrice(200).perRank(1).build()
        );
        when(httpSessionService.getUserFromSession()).thenReturn(user);
        when(userGasRecordDao.findSavingPriceByGenderAndAge(user.getGender(), user.getAge(), date)).thenReturn(Optional.of(rankSavingPrices));
        when(foodImageDao.findFoodImageByEcoPrice(BigDecimal.valueOf(200))).thenReturn(Optional.of(FoodImage.builder().food_no(1).imageUrl("http://example.com/food1.jpg").build()));

        // Act
        UserEcoRecordResDto result = userRecordService.calMonthUserEcoPrice();

        // Assert
        assertEquals("user1", result.getUserId());
        assertEquals(Gender.MALE, result.getGender());
        assertEquals(Age.FORTY, result.getAge());
        assertEquals(20000, result.getRefuelingPrice());
        assertEquals(200, result.getMyEcoPrice());
        assertEquals(320, result.getAverageEcoPrice());
        assertEquals("http://example.com/food1.jpg", result.getImageUrl());
        assertEquals(1, result.getRankPercentage(), 0.001);
    }
}