package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GasDetailTest {
    @Test
    @DisplayName("GasDetail 정적 팩터리 메소드 성공 테스트 (주유소)")
    void parseGasDetail() {
        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 평창문화로 135 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        System.out.println(input.length);
        LocalDate date = LocalDate.now();
        GasStation gasStation = GasStation.parseGasStation(input);
        List<GasDetail> gasDetail = GasDetail.parseListGasDetail(gasStation, input, date);

        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(gasStation, 1899, GasType.PREMIUM_GASOLINE, date));
        list.add(new GasDetail(gasStation, 1659, GasType.GASOLINE, date));
        list.add(new GasDetail(gasStation, 1759, GasType.DIESEL, date));
        Assertions.assertThat(gasDetail).usingRecursiveComparison().isEqualTo(list);
    }
}