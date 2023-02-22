package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import org.springframework.http.HttpStatus;

public class ParameterException extends DefaultException {
    public ParameterException(String className, Code code) {
        super(className, code, HttpStatus.BAD_REQUEST);
    }

    public ParameterException(Code code) {
        super(code, HttpStatus.BAD_REQUEST);
    }
}
