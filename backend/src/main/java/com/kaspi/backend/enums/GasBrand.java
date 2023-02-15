package com.kaspi.backend.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;


@Getter
public enum GasBrand {

    HYUNDIA_OIL_BANK("현대오일뱅크"),
    SK_ENERGY("SK에너지"),
    SK_GAS("SK가스"),
    GS_CALTEXT("GS칼텍스"),
    S_OIL("S-OIL"),
    ALTTEUL("알뜰주유소"),
    ALTTEUL_EX("알뜰(ex)"),
    NH_OIL("NH-OIL"),
    E1("E1"),
    CUSTOM("자가상표");

    private static String S3_URI = "https://team6-public-image.s3.ap-northeast-2.amazonaws.com/GasStation_DB/";

    private final String dbName;


    GasBrand(String dbName) {
        this.dbName = dbName;
    }

    //dbName을 기준으로 해당 이름에 맞는 image url을 가져옴
    public static String getImgByDbName(String gasBrand) {
        Optional<GasBrand> findGasBrand = Arrays.stream(values())
                .filter(value -> value.dbName.equals(gasBrand))
                .findAny();
        return S3_URI + findGasBrand.get().name()+".png";
    }
}
