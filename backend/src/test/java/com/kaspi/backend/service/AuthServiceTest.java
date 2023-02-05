package com.kaspi.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import java.util.Optional;
import org.assertj.core.api.Assertions;
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
}