package com.kaspi.backend.util.response.code;

public enum DefaultCode implements Code {

    Sample("S001", "샘플 메세지 입니다.");




    private String code;
    private String message;

    DefaultCode(String code, String message) {
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
