package com.kaspi.backend.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

class GasBrandTest {

    @Test
    @DisplayName("현트대오일뱅크 이미지 매핑 테스트")
    void hyundiatest()  {
        //given
        String brand = "현대오일뱅크";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/HYUNDIA_OIL_BANK.png");
    }

    @Test
    @DisplayName("알뜰주유소 이미지 매핑 테스트")
    void 알뜰주유소()  {
        //given
        String brand = "알뜰주유소";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/ALTTEUL.png");
    }

    @Test
    @DisplayName("알뜰주유소ex 이미지 매핑 테스트")
    void 알뜰주유소ex()  {
        //given
        String brand = "알뜰(ex)";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/ALTTEUL_EX.png");
    }

    @Test
    @DisplayName("custom 이미지 매핑 테스트")
    void custom()  {
        //given
        String brand = "자가상표";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/CUSTOM.png");
    }

    @Test
    @DisplayName("e1 이미지 매핑 테스트")
    void e1()  {
        //given
        String brand = "E1";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/E1.png");
    }

    @Test
    @DisplayName("gs칼텍스 이미지 매핑 테스트")
    void gs()  {
        //given
        String brand = "GS칼텍스";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/GS_CALTEXT.png");
    }

    @Test
    @DisplayName("Nh-oil 이미지 매핑 테스트")
    void nhoil()  {
        //given
        String brand = "NH-OIL";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/NH_OIL.png");
    }

    @Test
    @DisplayName("soil 이미지 매핑 테스트")
    void soil()  {
        //given
        String brand = "S-OIL";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/S_OIL.png");
    }

    @Test
    @DisplayName("skenergy 이미지 매핑 테스트")
    void skenergy()  {
        //given
        String brand = "SK에너지";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/SK_ENERGY.png");
    }

    @Test
    @DisplayName("skgas 이미지 매핑 테스트")
    void skgas()  {
        //given
        String brand = "SK가스";
        //when
        String img = GasBrand.getImgByDbName(brand);
        //then
        assertThat(img).isEqualTo("https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/SK_GAS.png");
    }
}