package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.dto.UserUpdateReqDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.exception.ParameterException;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserDao userDao;
    private final HttpSessionService httpSessionService;

    @Transactional
    public User makeUser(SignUpRequestDto signUpRequestDto) {
        Optional<Age> age = Age.getAge(signUpRequestDto.getAge());
        Optional<Gender> gender = Gender.getGender(signUpRequestDto.getGender());

        checkValidRequest(age, gender);

        log.info("회원가입 신청 유저:{}",signUpRequestDto);

        return userDao.save(User.builder()
                .id(signUpRequestDto.getId())
                .password(signUpRequestDto.getPassword())
                .gender(gender.get())
                .age(age.get()).build());
    }

    private void checkValidRequest(Optional<Age> age, Optional<Gender> gender) {
        if(age.isEmpty()|| gender.isEmpty()){
            throw new ParameterException(ErrorCode.PARAMETER_ERROR);
        }
    }

    public void updateUser(UserUpdateReqDto userUpdateReqDto) {
        User user = httpSessionService.getUserFromSession();
        Optional<Gender> gender = Gender.getGender(userUpdateReqDto.getGender());
        Optional<Age> age = Age.getAge(userUpdateReqDto.getAge());
        checkValidRequest(age,gender);
        user.updateUser(gender.get(), age.get());
        userDao.save(user);
    }

    public void deleteUser() {
        User user = httpSessionService.getUserFromSession();
        userDao.delete(user);
        log.info("유저 삭제: {}", user.toString());
        httpSessionService.deleteSession(); //세션도 삭제
    }


}
