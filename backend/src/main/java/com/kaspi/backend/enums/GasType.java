package com.kaspi.backend.enums;

public enum GasType {
    PREMIUM_GASOLINE("G01"),
    GASOLINE("G02"),
    DIESEL("G03"),
    LPG("G04");

    private String gasCode;

    GasType(String gasCode) {
        this.gasCode = gasCode;
    }
}
