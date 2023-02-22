package com.kaspi.backend.util.encrypt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    @DisplayName("비밀번호 생성 및 일치  성공")
    void testPwUtil() {
        //given
        String plainPw = "password";
        String encryptPw = PasswordUtil.makeEncryptPw(plainPw);
        //when
        //then
        Assertions.assertThat(PasswordUtil.matchPw(encryptPw, plainPw)).isTrue();
    }

    @Test
    @DisplayName("비밀번호 생성 및 불일치")
    void testPwUtilNotMatch() {
        //given
        String plainPw = "password";
        String encryptPw = PasswordUtil.makeEncryptPw(plainPw+"123");
        //when
        //then
        Assertions.assertThat(PasswordUtil.matchPw(encryptPw, plainPw)).isFalse();
    }
}