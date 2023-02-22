package com.kaspi.backend.dto.error;

import com.kaspi.backend.util.exception.DefaultException;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
@Getter
public class ErrorResponseDto {
    private final String timeStamp = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private final String className;
    private final String code;
    private final String message;

    public ErrorResponseDto(String className, String code, String message) {
        this.className = className;
        this.code = code;
        this.message = message;
    }

    public static ErrorResponseDto toResponse(DefaultException e) {
        return new ErrorResponseDto(e.getClassName(), e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }
}
