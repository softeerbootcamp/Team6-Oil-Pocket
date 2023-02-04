package com.kaspi.backend.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE,
    FEMALE;

    public static Optional<Gender> getGender(String requestGender) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(requestGender))
                .findAny();
    }


}
