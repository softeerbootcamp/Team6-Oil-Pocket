package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//인가
@Getter
@RequiredArgsConstructor
public class AuthorizationException extends RuntimeException {
    private final Code code;
}
