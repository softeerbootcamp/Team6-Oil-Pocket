package com.kaspi.backend.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum GasType {
    PREMIUM_GASOLINE("고급휘발유","고급휘발유"),
    GASOLINE("휘발유","휘발유"),
    DIESEL("자동차용경유","경유"),
    LPG("자동차용부탄","LPG");

    private String opinetGasType;
    private String dtoName;

    GasType(String opinetGasType,String dtoName) {
        this.opinetGasType = opinetGasType;
        this.dtoName = dtoName;
    }

    public static Optional<GasType> getTypeFromOpinetData(String opinetdata) {
        return Arrays.stream(values())
                .filter(value -> value.opinetGasType.equals(opinetdata))
                .findAny();
    }

}
