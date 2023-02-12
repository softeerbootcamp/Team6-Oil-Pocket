package com.kaspi.backend.util.response.code;

public enum ErrorCode implements Code {
    PARAMETER_ERROR("E001", "요청 데이터 형식이 올바르지 않습니다."),
    AUTH_ERROR("E002", "세션이 만료되었습니다"),
    NOT_VALID_USER("E003", "유저가 존재하지 않습니다.(회원 테이블에 존재하지 않는 유저)"),
    DUPLICATE_USER("E004", "중복된 아이디를 가진 유저가 존재합니다."),
    LOGIN_FAIL("E005", "아이디 혹은 패스워드가 다릅니다."),
    SQL_NOT_FOUND("E006", "쿼리문에서 특정값을 가져오지 못했습니다."),
    OPINET_EXCEPTION("E007", "오피넷에서 관련된 API요청에 문제가 생겼습니다"),
    NOT_FOUND_GAS_STATION("S001", "해당 주유소가 존재하지 않습니다."),
    NOT_FOUND_GAS_DETAIL("S002", "주유소 상세정보가 존재하지 않습니다.");



    private String code;
    private String message;

    ErrorCode(String code, String message) {
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
