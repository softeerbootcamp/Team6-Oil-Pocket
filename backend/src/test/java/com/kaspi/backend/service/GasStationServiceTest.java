package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationReqDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class GasStationServiceTest {
    @Mock
    private GasStationDao gasStationDao;
    @InjectMocks
    private GasStationService gasStationService;


    @Test
    @DisplayName("주유기록 입력시 주유소 검색 기능 테스트")
    void getGasStationByContainingName() {
        //given
        List<GasStation> gasStations = Arrays.asList(
                GasStation.builder().name("유진 주유소").build(),
                GasStation.builder().name("서울 유진 주유소").build(),
                GasStation.builder().name("서브웨이").build()
        );
        List<FindGasStationReqDto> expectedMatchingGasStations = Arrays.asList(
                FindGasStationReqDto.builder().name("유진 주유소").build(),
                FindGasStationReqDto.builder().name("서울 유진 주유소").build()
        );
        when(gasStationDao.findAll()).thenReturn(gasStations);
        //when
        List<FindGasStationReqDto> actualMatchingGasStations = gasStationService.getGasStationByContainingName("유진");
        //then
        assertThat(actualMatchingGasStations.size()).isEqualTo(2);
        assertThat(actualMatchingGasStations.get(0).getName()).isEqualTo(expectedMatchingGasStations.get(0).getName());
        assertThat(actualMatchingGasStations.get(1).getName()).isEqualTo(expectedMatchingGasStations.get(1).getName());
    }
}