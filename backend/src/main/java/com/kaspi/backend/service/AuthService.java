package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserDao userDao;

    public boolean checkValidUserId(String id) {
        Optional<User> findUser = userDao.findByUserId(id);
        log.info("중복유저 체크 ID:{}", id);
        return findUser.isEmpty();
    }

    public User signIn(SignInRequestDto signInRequestDto) {
        log.info("로그인 요청 id:{}, pw:{}", signInRequestDto.getId(), signInRequestDto.getPassword());
        Optional<User> findUser = userDao.findByUserId(signInRequestDto.getId());
        User existUser = checkNotValidUser(findUser);
        existUser.checkValidLogin(signInRequestDto,existUser.getPassword());
        return findUser.get();
    }

    public User checkNotValidUser(Optional<User> findUser) {
        if (findUser.isEmpty()) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_USER);
        }
        return findUser.get();
    }

}
