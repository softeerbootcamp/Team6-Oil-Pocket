package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class OpinetException extends DefaultException {
    public OpinetException(String className, Code code) {
        super(className, code, HttpStatus.GATEWAY_TIMEOUT);
    }

    public OpinetException(Code code) {
        super(code,HttpStatus.GATEWAY_TIMEOUT);
    }
}