package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationResDto;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                GasStation.builder().name("서울 유진 주유소").build()
        );
        List<FindGasStationResDto> expectedMatchingGasStations = Arrays.asList(
                FindGasStationResDto.builder().name("유진 주유소").build(),
                FindGasStationResDto.builder().name("서울 유진 주유소").build()
        );
        String requestGasStation = "유진";
        when(gasStationDao.findAllLikeName("%"+requestGasStation+"%")).thenReturn(gasStations);
        //when
        List<FindGasStationResDto> actualMatchingGasStations = gasStationService.getGasStationByContainingName(requestGasStation);
        //then
        assertThat(actualMatchingGasStations.size()).isEqualTo(2);
        assertThat(actualMatchingGasStations.get(0).getName()).isEqualTo(expectedMatchingGasStations.get(0).getName());
        assertThat(actualMatchingGasStations.get(1).getName()).isEqualTo(expectedMatchingGasStations.get(1).getName());
    }

    @Test
    @DisplayName("PK값으로 GasStation 객체 얻기 성공")
    void getGasStationByNo() {
        //given
        Long requestGasStationNo = 1L;
        GasStation gasStation = GasStation.builder()
                .stationNo(requestGasStationNo)
                .build();
        when(gasStationDao.findById(requestGasStationNo)).thenReturn(Optional.of(gasStation));
        //when
        GasStation findGasStation = gasStationService.getGasStationByNo(requestGasStationNo);
        //then
        assertThat(findGasStation).usingRecursiveComparison().isEqualTo(gasStation);
    }

    @Test
    @DisplayName("PK값으로 GasStation 객체 얻기 예외")
    void getGasStationByNo_Exception() {
        //given
        Long requestGasStationNo = 1L;
        GasStation gasStation = GasStation.builder()
                .stationNo(requestGasStationNo)
                .build();
        when(gasStationDao.findById(requestGasStationNo)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            gasStationService.getGasStationByNo(requestGasStationNo);
        });
    }
}