package com.kaspi.backend.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenderTest {

    @Test
    @DisplayName("요청데이터에 대한 gender객체 변환 테스트")
    void getGender() {
        //given
        String requestGender = "M";
        //when
        Optional<Gender> gender = Gender.getGender(requestGender);
        //then
        assertThat(gender.isPresent()).isTrue();
        assertThat(gender.get()).isEqualTo(Gender.MALE);
    }
}