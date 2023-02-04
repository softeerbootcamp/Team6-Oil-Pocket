package com.kaspi.backend.service;

import com.kaspi.backend.domain.User;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpSessionService {
    public static final String SESSION_KEY = "userId";

    private final HttpSession httpSession;


    public void makeHttpSession(Long userId) {
        httpSession.setAttribute(SESSION_KEY, userId);
    }



}
