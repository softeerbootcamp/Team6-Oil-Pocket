package com.kaspi.backend.util.exception;

import com.kaspi.backend.util.response.code.Code;

public class SqlNotFoundException extends DefaultException{
    public SqlNotFoundException(String className, Code code) {
        super(className, code);
    }

    public SqlNotFoundException(Code code) {
        super(code);
    }
}
