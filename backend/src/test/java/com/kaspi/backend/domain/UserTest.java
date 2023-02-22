package com.kaspi.backend.domain;

import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.encrypt.PasswordUtil;
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
        String encryptPw = PasswordUtil.makeEncryptPw("password");
        //when
        //then
        Assertions.assertDoesNotThrow(()->matchUser.checkValidLogin(signInRequestDto,encryptPw));
    }

    @Test
    @DisplayName("유저 정보 변경")
    void updateUser() {
        //given
        User user = User.builder().age(Age.TWENTY).gender(Gender.MALE).build();
        //when
        user.updateUser(Gender.FEMALE, Age.FIFTY);
        //then
        assertEquals(user.getGender(),Gender.FEMALE);
        assertEquals(user.getAge(),Age.FIFTY);
    }
}