package com.kaspi.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.cookie.CookieFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private CookieFactory cookieFactory;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("유저 정상 생성 테스트")
    void makeUser() {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder().id("user1").password("password").gender("M")
                .age("20대")
                .build();
        User expectedUser = User.builder().userNo(1L).id("user1").password("password").age(Age.TWENTY)
                .gender(Gender.MALE)
                .build();
        when(userDao.insertUser(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(expectedUser);
        //when
        User actualUser = userService.makeUser(signUpRequestDto);
        //then
        verify(userDao).insertUser(signUpRequestDto.getId(),
                signUpRequestDto.getPassword(),
                Gender.getGender(signUpRequestDto.getGender()).get().name(),
                Age.getAge(signUpRequestDto.getAge()).get().name());
        assertEquals(expectedUser, actualUser);
    }
}