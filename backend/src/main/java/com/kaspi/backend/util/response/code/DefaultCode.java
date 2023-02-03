package com.kaspi.backend.util.response.code;

public enum DefaultCode implements Code {

    SUCCESS_SIGNUP("L001", "정상적으로 회원가입이 완료되었습니다.");




    private String code;
    private String message;

    DefaultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
