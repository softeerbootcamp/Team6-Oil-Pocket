package com.kaspi.backend.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String initial;

    Gender(String initial) {
        this.initial = initial;
    }


    public static Optional<Gender> getGender(String requestGender) {
        return Arrays.stream(values())
                .filter(value -> value.initial.equals(requestGender))
                .findAny();
    }


}
