package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.enums.GasType;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class GasDetailServiceTest {
    @InjectMocks
    private GasDetailService gasDetailService;

    @Mock
    private GasDetailDao gasDetailDao;

    @Mock
    private GasStationDao gasStationDao;
    private LocalDate date;
    List<GasDetail> list;

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
    }


    @Test
    @DisplayName("findGasDetailList 성공 테스트")
    void findGasDetailListSuccess() {
        //given
        List<GasDetailDto> gasDetailDtoList = GasDetailDto.newDtoList(list);

        //when
        when(gasDetailDao.findByStationNoAndDate(1L, date))
                .thenReturn(Optional.of(list));
        when(gasStationDao.findByAddressAndBrand("평창문화로 135", "현대오일뱅크"))
                .thenReturn(Optional.of(new GasStation(1L, "서울 종로구", "㈜지에스이앤알 평창주유소", "평창문화로 135", "현대오일뱅크", true)));
        List<GasDetailDto> result = gasDetailService.findGasDetailList("평창문화로", "135", "현대오일뱅크");

        //then
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(0));
        softly.assertThat(result.get(1)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(1));
        softly.assertThat(result.get(2)).usingRecursiveComparison().isEqualTo(gasDetailDtoList.get(2));
        softly.assertAll();
    }
}