package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.code.ErrorCode;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class GasDetailServiceTest {
    @InjectMocks
    GasDetailService gasDetailService;
    @Mock
    GasDetailDao gasDetailDao;
    @Mock
    GasStationDao gasStationDao;
    LocalDate date;
    List<GasDetail> list;
    List<GasDetailDto> gasDetailDtoList;
    GasStation gasStation;


    @BeforeEach
    void setUp() {
        //given
        date = LocalDate.now();
        GasDetail gasDetail1 = GasDetail.builder()
                .gasType(GasType.GASOLINE).price(1600).date(date).stationNo(1L).build();
        GasDetail gasDetail2 = GasDetail.builder()
                .gasType(GasType.PREMIUM_GASOLINE).price(1600).date(date).stationNo(1L).build();
        GasDetail gasDetail3 = GasDetail.builder()
                .gasType(GasType.DIESEL).price(1600).date(date).stationNo(1L).build();
        GasDetail gasDetail4 = GasDetail.builder()
                .gasType(GasType.LPG).price(1600).date(date).stationNo(1L).build();

        list = new ArrayList<>();
        list.add(gasDetail1);
        list.add(gasDetail2);
        list.add(gasDetail3);
        list.add(gasDetail4);

        gasDetailDtoList = GasDetailDto.newDtoList(list);
        gasStation = new GasStation(1L, "서울 종로구", "㈜지에스이앤알 평창주유소", "평창문화로 135", "현대오일뱅크", true);
    }

    @Test
    @DisplayName("findGasDetailList 성공 테스트")
    void findGasDetailListSuccess() {
        //when
        when(gasDetailDao.findByStationNoAndDate(1L, date))
                .thenReturn(Optional.of(list));
        List<GasDetailDto> result = gasDetailService.findGasDetailList(gasStation);

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(0));
        softly.assertThat(result.get(1)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(1));
        softly.assertThat(result.get(2)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(2));
        softly.assertThat(result.get(3)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(3));
        softly.assertAll();
    }

    @Test
    @DisplayName("findGasDetailList 주유소 정보 not found 실패 테스트")
    void findGasDetailListFailGasDetail() {
        when(gasDetailDao.findByStationNoAndDate(1L, date))
                .thenReturn(Optional.empty());

        SqlNotFoundException exception = assertThrows(SqlNotFoundException.class, () -> {
            gasDetailService.findGasDetailList(gasStation);
        });
        assertEquals(ErrorCode.NOT_FOUND_GAS_DETAIL.getMessage(), exception.getErrorCode().getMessage());
    }
    @Test
    @DisplayName("findOneMonthGasDetailList 주유소 1달 정보 성공 테스트")
    void findOneMonthGasDetailList() {
        //when
        when(gasDetailDao.findByStationNoAndDate(1L, date))
                .thenReturn(Optional.of(list));
        List<GasDetailDto> result = gasDetailService.findGasDetailList(gasStation);

        //then
        // 1달 정보에서는 빈 유종을 default 0으로 채워주지 않습니다.
        // 즉 존재하는 유종 정보만 전달합니다.
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(0));
        softly.assertThat(result.get(1)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(1));
        softly.assertThat(result.get(2)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(2));
        softly.assertAll();
    }
}