package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;

    public boolean checkValidUserId(String id) {
        Optional<User> findUser = userDao.findByUserId(id);
        return findUser.isEmpty();
    }

    public User signIn(SignInRequestDto signInRequestDto) {
        Optional<User> findUser = userDao.findByUserId(signInRequestDto.getId());
        checkNotValidUser(findUser);
        checkIdPassword(signInRequestDto, findUser.get());
        return findUser.get();
    }

    private void checkIdPassword(SignInRequestDto signInRequestDto, User user) {
        if (!user.getId().equals(signInRequestDto.getId())
                || !user.getPassword().equals(signInRequestDto.getPassword())) {
            throw new AuthenticationException(ErrorCode.LOGIN_FAIL);
        }
    }

    public void checkNotValidUser(Optional<User> findUser) {
        if (findUser.isEmpty()) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_USER);
        }
    }

}
