package com.kaspi.backend.domain;

import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.encrypt.PasswordUtil;
import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "users")
public class User {

    @Id
    private Long userNo;
    private String id;
    private String password;

    private Gender gender;

    private Age age;


    public void checkValidLogin(SignInRequestDto signInRequestDto,String encryptPassword) {
        if (!this.getId().equals(signInRequestDto.getId())
                || !PasswordUtil.matchPw(encryptPassword,signInRequestDto.getPassword())) {
            throw new AuthenticationException(ErrorCode.LOGIN_FAIL);
        }
    }

    public void updateUser(Gender gender, Age age) {
        this.gender = gender;
        this.age = age;
    }
}
