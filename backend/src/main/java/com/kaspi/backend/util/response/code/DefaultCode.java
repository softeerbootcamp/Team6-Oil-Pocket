package com.kaspi.backend.util.response.code;

public enum DefaultCode implements Code {

    SUCCESS_SIGNUP("L001", "정상적으로 회원가입이 완료되었습니다."),
    SUCCESS_VALID_ID("L002", "사용가능한 아이디입니다."),
    SUCCESS_SIGN_IN("L003", "로그인 성공(세션 생성)"),
    DELETE_SESSION("L004", "로그아웃 성공(세션 삭제)"),
    CHECK_MATCH_GAS_STATION("M001", "사용자 요청과 일치하는 데이터 조회 성공"),
    SAVE_USER_GAS_RECORD("M002", "유저의 주유데이터 저장 성공"),
    GET_USER_GAS_RECORDS("M003", "유저의 주유데이터 조회 성공"),
    SUCCESS_FIND_USER_ECO_RECORD("M004", "유저의 절약 금액 조회 성공"),
    SUCCESS_FIND_USER_RECORDS_MONTH("M005", "유저의 주유기록 월별비교 데이터 조회 성공"),
    SUCCESS_FIX_USER("M006", "유저 정보 수정"),

    DELETE_USER("M007", "유저삭제 성공"),

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
