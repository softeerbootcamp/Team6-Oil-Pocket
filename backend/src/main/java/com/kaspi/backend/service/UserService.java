package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.cookie.CookieFactory;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final CookieFactory cookieFactory;

    private static final String SESSION_KEY = "user_no";
    private static final String SESSION_COOKIE_KEY = "sid";
    private static int SESSION_COOKIE_VALID_TIME = 3600;

    public ResponseCookie makeCookieFromSession(Long userNo,HttpSession session) {
        session.setAttribute(SESSION_KEY, userNo);
        ResponseCookie responseSessionCookie = cookieFactory.makeResponseCookie(SESSION_COOKIE_KEY, session.getId(), SESSION_COOKIE_VALID_TIME);
        return responseSessionCookie;
    }

    @Transactional
    public Long makeUser(SignUpRequestDto signUpRequestDto) {
        Optional<Age> age = Age.getAge(signUpRequestDto.getAge());
        Optional<Gender> gender = Gender.getGender(signUpRequestDto.getGender());

        if(age.isEmpty()||gender.isEmpty()){
            throw new IllegalArgumentException(ErrorCode.PARAMETER_ERROR.getMessage());
        }


        return userDao.insertUser(signUpRequestDto.getId(),
                signUpRequestDto.getPassword(),
                gender.get().name(),
                age.get().name());
    }
}
