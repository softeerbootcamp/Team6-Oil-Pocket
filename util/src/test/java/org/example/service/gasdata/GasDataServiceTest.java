package org.example.service.gasdata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GasDataServiceTest {
    @InjectMocks
    GasDataService gasDataService;

    @Test
    @DisplayName("키 값 만들기 테스트 : ")
    void makeKeyNomal() {
        String str1 = "A0000035,서울 종로구,㈜지에스이앤알 평창주유소,서울 종로구 평창문화로 135 (평창동),현대오일뱅크,셀프,1899,1659,1759,0";
        String str2 = "A0000045,서울 종로구,SK북악주유소,서울 종로구 평창문화로 137,SK에너지,일반,2328,2128,2258,0";

        String[] attribute = str1.split(",");
        String key = gasDataService.makeCacheKey(attribute);
        Assertions.assertThat(key).isEqualTo("평창문화로 135:현대오일뱅크");
    }
}