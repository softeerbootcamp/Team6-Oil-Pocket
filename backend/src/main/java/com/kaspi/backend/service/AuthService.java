package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
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

}
