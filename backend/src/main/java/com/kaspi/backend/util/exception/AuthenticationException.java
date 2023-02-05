package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//인증
@Getter
@RequiredArgsConstructor
public class AuthenticationException extends RuntimeException {
    private final Code code;
}