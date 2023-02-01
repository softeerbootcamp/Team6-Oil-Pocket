package com.kaspi.backend.util.response;

import com.kaspi.backend.util.response.code.Code;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class CommonResponseDto {
    private final String timeStamp = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String code;
    private final String message;
    private final Object data;


    public CommonResponseDto(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResponseDto toResponse(Code code, Object data) {
        return new CommonResponseDto(code.getCode(), code.getMessage(), data);
    }



}