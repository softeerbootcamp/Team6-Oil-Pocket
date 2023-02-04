package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional
    public User makeUser(SignUpRequestDto signUpRequestDto) {
        Optional<Age> age = Age.getAge(signUpRequestDto.getAge());
        Optional<Gender> gender = Gender.getGender(signUpRequestDto.getGender());

        if(age.isEmpty()||gender.isEmpty()){
            throw new IllegalArgumentException(ErrorCode.PARAMETER_ERROR.getMessage());
        }

        return userDao.save(User.builder()
                .id(signUpRequestDto.getId())
                .password(signUpRequestDto.getPassword())
                .gender(gender.get())
                .age(age.get()).build());
    }


}
