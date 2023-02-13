package com.kaspi.backend.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
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
