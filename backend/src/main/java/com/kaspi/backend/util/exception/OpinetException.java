package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpinetException extends RuntimeException {
    private final Code code;
}