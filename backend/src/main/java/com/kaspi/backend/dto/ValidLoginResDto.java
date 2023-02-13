package com.kaspi.backend.dto;

import com.kaspi.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidLoginResDto {
    private String userId;
    private String gender;
    private String age;

    public static ValidLoginResDto toValidLoginResDto(User user) {
        return new ValidLoginResDto(user.getId(), user.getGender().getInitial(), user.getAge().getAgeBound());
    }
}
