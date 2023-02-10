package com.kaspi.backend.util.response.code;

public enum DefaultCode implements Code {

    SUCCESS_SIGNUP("L001", "정상적으로 회원가입이 완료되었습니다."),
    SUCCESS_VALID_ID("L002", "사용가능한 아이디입니다."),
    SUCCESS_SIGN_IN("L003", "로그인 성공(세션 생성)"),
    SUCCESS_TO_FIND_GAS_DEATIL("G001", "주유소 가격 상세정보를 찾았습니다.");
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
