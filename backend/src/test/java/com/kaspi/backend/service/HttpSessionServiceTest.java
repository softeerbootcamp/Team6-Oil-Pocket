package com.kaspi.backend.service;

import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HttpSessionServiceTest {

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private HttpSessionService httpSessionService;

    @Test
    public void testMakeHttpSession() {
        // Given
        Long userId = 1L;
        // When
        httpSessionService.makeHttpSession(userId);
        // Then
        verify(httpSession).setAttribute(HttpSessionService.SESSION_KEY, userId);
    }
}
