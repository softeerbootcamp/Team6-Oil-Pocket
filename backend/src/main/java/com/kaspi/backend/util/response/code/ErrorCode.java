package com.kaspi.backend.util.response.code;

public enum ErrorCode implements Code {
    LOGIN_ERROR("E001", "에러 샘플");


    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
