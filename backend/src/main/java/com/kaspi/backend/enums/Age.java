package com.kaspi.backend.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Age {

    TWENTY("20대"),
    THIRTY("30대"),
    FORTY("40대"),
    FIFTY("50대"),
    SIXTY_OVER("60대 이상");

    private final String ageBound;

    Age(String ageBound) {
        this.ageBound = ageBound;
    }

    public static Optional<Age> getAge(String requestAge) {
        return Arrays.stream(values())
                .filter(value -> value.ageBound.equals(requestAge))
                .findAny();
    }
}
