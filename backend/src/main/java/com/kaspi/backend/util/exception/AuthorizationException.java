package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

//인가
public class AuthorizationException extends DefaultException {
    public AuthorizationException(String className, Code code) {
        super(className, code, HttpStatus.UNAUTHORIZED);
    }

    public AuthorizationException(Code code) {
        super(code,HttpStatus.UNAUTHORIZED);
    }
}