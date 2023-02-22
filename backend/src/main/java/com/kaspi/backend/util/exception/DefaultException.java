package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DefaultException extends RuntimeException {
    private String className;

    private HttpStatus httpStatus;
    private Code errorCode;

    public DefaultException(String className, Code code,HttpStatus httpStatus) {
        super(code.getMessage());
        this.className = className;
        this.errorCode = code;
        this.httpStatus = httpStatus;
    }

    public DefaultException(Code code,HttpStatus httpStatus) {
        super(code.getMessage());
        this.className = this.getClass().getSimpleName();
        this.errorCode = code;
        this.httpStatus = httpStatus;
    }
}
