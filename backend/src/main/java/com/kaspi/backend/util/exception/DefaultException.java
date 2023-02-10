package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Data;

@Data
public class DefaultException extends RuntimeException {
    private String className;
    private Code errorCode;

    public DefaultException(String className, Code code) {
        super(code.getMessage());
        this.className = className;
        this.errorCode = code;
    }

    public DefaultException(Code code) {
        super(code.getMessage());
        this.className = this.getClass().getSimpleName();
        this.errorCode = code;
    }
}
