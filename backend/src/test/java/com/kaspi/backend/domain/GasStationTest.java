package com.kaspi.backend.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasStationTest {
    @Test
    @DisplayName("주유소 파싱 테스트")
    void test() {
        String str1 = "A0000035,서울 종로구,㈜지에스이앤알 평창주유소,서울 종로구 평창문화로 135 (평창동),현대오일뱅크,셀프,1899,1659,1759,0";
        String str2 = "A0000045,서울 종로구,SK북악주유소,서울 종로구 평창문화로 137,SK에너지,일반,2328,2128,2258,0";

        String[] attribute = str1.split(",");
        GasStation testGasStation1 = GasStation.parseGasStation(attribute);
        GasStation gasStation1 = new GasStation("서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 평창문화로 135 (평창동)","현대오일뱅크",true);
        attribute = str2.split(",");
        GasStation testGasStation2 = GasStation.parseGasStation(attribute);
        GasStation gasStation2 = new GasStation ("서울 종로구", "SK북악주유소", "서울 종로구 평창문화로 137 (평창동)","SK에너지",false);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(testGasStation1).usingRecursiveComparison().isEqualTo(gasStation1);
        softAssertions.assertThat(testGasStation2).usingRecursiveComparison().isEqualTo(gasStation2);
    }
}