package com.kaspi.backend.util.response.code;

public enum DefaultCode implements Code {

    SUCCESS_SIGNUP("L001", "정상적으로 회원가입이 완료되었습니다."),
    SUCCESS_VALID_ID("L002", "사용가능한 아이디입니다."),
    SUCCESS_SIGN_IN("L003", "로그인 성공(세션 생성)"),
    CHECK_MATCH_GAS_STATION("M001", "사용자 요청과 일치하는 데이터 조회 성공"),
    SAVE_USER_GAS_RECORD("U001", "유저의 주유데이터 저장 성공"),
    SUCCESS_FIND_USER_ECO_RECORD("U002", "유저의 절약 금액 조회 성공"),
    SUCCESS_TO_FIND_GAS_DETAIL("G001", "주유소 가격 상세정보를 찾았습니다.");

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
