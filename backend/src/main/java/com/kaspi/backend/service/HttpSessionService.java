package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HttpSessionService {
    public static final String SESSION_KEY = "userNo";

    private final HttpSession httpSession;
    private final UserDao userDao;
    private final AuthService authService;


    public void makeHttpSession(Long userNo) {
        httpSession.setAttribute(SESSION_KEY, userNo);
        log.info("세션 생성 - 요청 userNO:{}",userNo);
    }

    public User getUserFromSession() {
        Long userNo = (Long) httpSession.getAttribute(SESSION_KEY);
        checkSession(userNo);
        Optional<User> findUser = userDao.findById(userNo);
        authService.checkNotValidUser(findUser);
        return findUser.get();
    }

    private static void checkSession(Long userNo) {
        if (userNo == null) {
            log.error("세션이 만료되었습니다.");
            throw new AuthenticationException(ErrorCode.AUTH_ERROR);
        }
    }

    public void deleteSession() {
        httpSession.invalidate();
    }


}
