package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class DefaultException extends RuntimeException{
    private String className;
    private String errorCode;
    private String errorMessage;
    //논의 후 HttpStatus 필드도 추가 예정
    //private HttpStatus httpStatus;

    public DefaultException(String className, Code code) {
        this.className = className;
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage();
    }
    public DefaultException(Code code) {
        this.className = this.getClass().getSimpleName();
        this.errorCode = code.getCode();
        this.errorMessage = code.getMessage();
    }
}
