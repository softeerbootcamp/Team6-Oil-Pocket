package com.kaspi.backend.domain;

import com.kaspi.backend.dto.SignInRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("아이디 패스워드 확인 테스트")
    void checkValidLogin() {
        //given
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().id("test").password("password").build();
        User matchUser = User.builder().id("test").password("password").build();
        //when
        //then
        Assertions.assertDoesNotThrow(()->matchUser.checkValidLogin(signInRequestDto));
    }
}