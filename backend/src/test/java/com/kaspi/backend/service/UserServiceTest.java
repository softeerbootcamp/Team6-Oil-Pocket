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
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseCookie;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private CookieFactory cookieFactory;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private UserService userService;

    private static final String SESSION_KEY = "User";
    private static final String SESSION_COOKIE_KEY = "sid";
    private static int SESSION_COOKIE_VALID_TIME = 3600;

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


    @Test
    @DisplayName("유저 비정상 생성 테스트 에러 생성")
    void makeNoTValidUser() {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder().id("user1").password("password").gender("Male")
                .age("20대")
                .build();
        assertThrows(IllegalArgumentException.class, () -> {
            userService.makeUser(signUpRequestDto);
        });
    }

    @Test
    @DisplayName("세션을 이용하여 정상적 쿠키 생성 테스트")
    void makeCookieFromSession() {
        //given
        User user = User.builder().userNo(1L).id("user1").password("password").age(Age.TWENTY)
                .gender(Gender.MALE)
                .build();
        ResponseCookie expectedCookie = ResponseCookie.from(SESSION_COOKIE_KEY, "session_id")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(SESSION_COOKIE_VALID_TIME)
                .build();

        //when
        when(httpSession.getId()).thenReturn("session_id");
        when(cookieFactory.makeResponseCookie(SESSION_COOKIE_KEY, "session_id", SESSION_COOKIE_VALID_TIME))
                .thenReturn(expectedCookie);
        ResponseCookie responseCookie = userService.makeCookieFromSession(user, httpSession);

        //then
        verify(httpSession).setAttribute(SESSION_KEY, user);
        verify(cookieFactory).makeResponseCookie(SESSION_COOKIE_KEY, "session_id", SESSION_COOKIE_VALID_TIME);
        assertEquals(expectedCookie, responseCookie);
    }
}