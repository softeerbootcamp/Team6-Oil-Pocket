package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;
import org.springframework.http.HttpStatus;

public class SqlNotFoundException extends DefaultException{
    public SqlNotFoundException(String className, Code code) {
        super(className, code, HttpStatus.NOT_FOUND);
    }

    public SqlNotFoundException(Code code) {
        super(code,HttpStatus.NOT_FOUND);
    }
}
