package com.kaspi.backend.dto;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidLoginResDtoTest {

    @Test
    @DisplayName("유효한 로그인경우 user객체를 validLoginResDto로 변경 로직")
    void toValidLoginResDto() {
        //given
        User user = User.builder()
                .id("test")
                .gender(Gender.MALE)
                .age(Age.TWENTY)
                .build();
        //when
        ValidLoginResDto validLoginResDto = ValidLoginResDto.toValidLoginResDto(user);
        //then
        assertThat(validLoginResDto.getUserId()).isEqualTo(user.getId());
        assertThat(validLoginResDto.getGender()).isEqualTo(user.getGender().getInitial());
        assertThat(validLoginResDto.getAge()).isEqualTo(user.getAge().getAgeBound());
    }
}