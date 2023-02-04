package com.kaspi.backend.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HttpSessionServiceTest {

    @Mock
    private HttpSession httpSession;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private HttpSessionService httpSessionService;


    @Test
    @DisplayName("세션 생성 로직 테스트")
    public void testMakeHttpSession() {
        // Given
        Long userId = 1L;
        // When
        httpSessionService.makeHttpSession(userId);
        // Then
        verify(httpSession).setAttribute(HttpSessionService.SESSION_KEY, userId);
    }

    @Test
    @DisplayName("세션으로부터 유저를 가지고 오는 로직 테스트")
    void getUserFromSession() {
        // Given
        Long userNo = 1L;
        when(httpSession.getAttribute(HttpSessionService.SESSION_KEY)).thenReturn(userNo);
        User user = User.builder().userNo(userNo).build();
        when(userDao.findById(userNo)).thenReturn(Optional.of(user));

        // When
        User resultUser = httpSessionService.getUserFromSession();

        // Then
        verify(httpSession).getAttribute(HttpSessionService.SESSION_KEY);
        verify(userDao).findById(userNo);
        assertThat(user).isEqualTo(resultUser);
    }
}
