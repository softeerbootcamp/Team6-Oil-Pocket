package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

//인증
public class AuthenticationException extends DefaultException {
    public AuthenticationException(String className, Code code) {
        super(className, code, HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationException(Code code) {
        super(code,HttpStatus.UNAUTHORIZED);
    }
}