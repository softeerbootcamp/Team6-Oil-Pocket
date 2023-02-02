package com.kaspi.backend.domain;

public enum Age {

    TWENTY("20대"),
    THIRTY("30대"),
    FORTY("40대"),
    FIFTY("50대"),
    SIXTY_OVER("60대 이상");

    Age(String ageBound) {
        this.ageBound = ageBound;
    }

    private String ageBound;

    public String getAgeBound() {
        return ageBound;
    }
}
