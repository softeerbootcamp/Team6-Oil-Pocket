package com.kaspi.backend.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GasType {
    PREMIUM_GASOLINE("고급휘발유"),
    GASOLINE("휘발유"),
    DIESEL("자동차용경유"),
    LPG("자동차용부탄");

    private String opinetGasType;

    GasType(String opinetGasType) {
        this.opinetGasType = opinetGasType;
    }

    public static GasType getTypeFromOpinetData(String opinetdata) {
        return Arrays.stream(values())
                .filter(value -> value.opinetGasType.equals(opinetdata))
                .findAny().get();
    }

}
