package com.kaspi.backend.enums;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgeTest {

    @Test
    @DisplayName("요청데이터에 대한 Age객체 변환 테스트")
    void getAge() {
        //given
        String requestAge = "20대";
        //when
        Optional<Age> age = Age.getAge(requestAge);
        //then
        assertThat(age.isPresent()).isTrue();
        assertThat(age.get()).isEqualTo(Age.TWENTY);
    }
}