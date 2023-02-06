package com.kaspi.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController signUpController;

    @Mock
    private UserService userService;

    @Mock
    private HttpSessionService httpSessionService;

    @Test
    @DisplayName("회원가입 api 성공 테스트")
    void testSignUp_SUCCESS(){
        // given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .id("test")
                .password("password")
                .age("20대")
                .gender("M").build();
        User makeUser = User.builder()
                .userNo(1L)
                .id("test")
                .password("password")
                .age(Age.TWENTY)
                .gender(Gender.MALE)
                .build();

        doReturn(makeUser).when(userService).makeUser(signUpRequestDto);
        doNothing().when(httpSessionService).makeHttpSession(makeUser.getUserNo());
        // when
        ResponseEntity<CommonResponseDto> response = signUpController.signUp(signUpRequestDto);
        // then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(DefaultCode.SUCCESS_SIGNUP.getCode(),response.getBody().getCode());
        assertEquals(DefaultCode.SUCCESS_SIGNUP.getMessage(),response.getBody().getMessage());
        verify(userService).makeUser(signUpRequestDto);
        verify(httpSessionService).makeHttpSession(makeUser.getUserNo());
    }
}