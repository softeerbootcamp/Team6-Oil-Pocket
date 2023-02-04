package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.util.exception.NotValidUserException;
import com.kaspi.backend.util.response.code.ErrorCode;
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


    public void makeHttpSession(Long userId) {
        httpSession.setAttribute(SESSION_KEY, userId);
    }

    public User getUserFromSession() {
        Long userNo = (Long) httpSession.getAttribute(SESSION_KEY);
        Optional<User> findUser = userDao.findById(userNo);
        checkNotValidUser(findUser);
        return findUser.get();
    }

    private void checkNotValidUser(Optional<User> findUser) {
        if (findUser.isEmpty()) {
            throw new NotValidUserException(ErrorCode.NOT_VALID_USER);
        }
    }


}
