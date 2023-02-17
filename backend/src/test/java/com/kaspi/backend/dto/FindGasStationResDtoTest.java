package com.kaspi.backend.dto;

import com.kaspi.backend.enums.GasBrand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindGasStationResDtoTest {

    @Test
    @DisplayName("주유소 검색 메서드 custom equal")
    void testEquals() {
        //given
        FindGasStationResDto origin = FindGasStationResDto.builder().stationNo(1L).brand(GasBrand.SK_GAS.getDbName()).build();
        FindGasStationResDto opposite = FindGasStationResDto.builder().stationNo(1L).brand(GasBrand.SK_ENERGY.getDbName()).build();
        //when
        //then
        Assertions.assertThat(origin.equals(opposite)).isTrue();
    }

    @Test
    @DisplayName("주유소 검색 메서드 custom equal 실패")
    void testEquals_Fail() {
        //given
        FindGasStationResDto origin = FindGasStationResDto.builder().stationNo(1L).brand(GasBrand.SK_GAS.getDbName()).build();
        FindGasStationResDto opposite = FindGasStationResDto.builder().stationNo(2L).brand(GasBrand.SK_ENERGY.getDbName()).build();
        //when
        //then
        Assertions.assertThat(origin.equals(opposite)).isFalse();
    }

    @Test
    @DisplayName("주유소 검색 메서드 List에서 테스트")
    void testEquals_List() {
        //given
        FindGasStationResDto origin = FindGasStationResDto.builder().stationNo(1L).brand(GasBrand.SK_GAS.getDbName()).build();
        FindGasStationResDto opposite = FindGasStationResDto.builder().stationNo(1L).brand(GasBrand.SK_ENERGY.getDbName()).build();
        List<FindGasStationResDto> list = List.of(origin);
        //when
        //then
        Assertions.assertThat(list.contains(opposite)).isTrue();
    }
}