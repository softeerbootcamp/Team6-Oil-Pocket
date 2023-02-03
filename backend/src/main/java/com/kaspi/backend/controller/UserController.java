package com.kaspi.backend.controller;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @PostMapping("")
    public ResponseEntity<CommonResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userDao.insertUser(signUpRequestDto.getId(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getGender(),
                signUpRequestDto.getAge()
        );

        return new ResponseEntity(
                CommonResponseDto.toResponse(DefaultCode.SUCCESS_SIGNUP),
                HttpStatus.CREATED
                );
    }

}
