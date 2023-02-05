package com.kaspi.backend.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.util.exception.AuthenticationException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("사용자 아이디가 사용불가능한 ID경우")
    void checkValidUserId() {
        when(userDao.findByUserId("existUserID")).thenReturn(Optional.of(new User()));
        boolean result = authService.checkValidUserId("existUserID");
        assertFalse(result);
    }

    @Test
    @DisplayName("사용자 아이디가 사용가능한 ID인 경우")
    void checkInValidUserId() {
        when(userDao.findByUserId("notExistUserID")).thenReturn(Optional.empty());
        boolean result = authService.checkValidUserId("notExistUserID");
        assertTrue(result);
    }

    @Test
    @DisplayName("유효한 로그인 로직 테스트")
    public void validSingIn() {
        //given
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().id("test").password("password").build();
        User existUser = User.builder().id("test").password("password").build();
        Optional<User> findUser = Optional.of(existUser);
        when(userDao.findByUserId(signInRequestDto.getId())).thenReturn(findUser);
        //when
        User actualUser = authService.signIn(signInRequestDto);
        //then
        assertThat(actualUser.getId()).isEqualTo(signInRequestDto.getId());
        assertThat(actualUser.getPassword()).isEqualTo(signInRequestDto.getPassword());
    }

    @Test
    @DisplayName("유효하지 않은 로그인-유저가 존재하지 않는경우")
    public void InvalidSignInNotExistUser() {
        //given
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().id("test").password("password").build();
        Optional<User> findUser = Optional.empty();
        when(userDao.findByUserId(signInRequestDto.getId())).thenReturn(findUser);
        //when,then
        assertThrows(AuthenticationException.class, () -> authService.signIn(signInRequestDto));
    }

    @Test
    @DisplayName("유효하지 않은 로그인-아이디 패스워드가 틀린경우")
    public void InvalidSignInNotCorrectPassword() {
        //given
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().id("test").password("password").build();
        User existUser = User.builder().id("test").password("password2").build();
        Optional<User> findUser = Optional.of(existUser);
        when(userDao.findByUserId(signInRequestDto.getId())).thenReturn(findUser);
        //when,then
        assertThrows(AuthenticationException.class, () -> authService.signIn(signInRequestDto));
    }
}