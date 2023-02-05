package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpSessionService {
    public static final String SESSION_KEY = "userNo";

    private final HttpSession httpSession;
    private final UserDao userDao;
    private final AuthService authService;


    public void makeHttpSession(Long userNo) {
        httpSession.setAttribute(SESSION_KEY, userNo);
    }

    public User getUserFromSession() {
        Long userNo = (Long) httpSession.getAttribute(SESSION_KEY);
        Optional<User> findUser = userDao.findById(userNo);
        authService.checkNotValidUser(findUser);
        return findUser.get();
    }




}
