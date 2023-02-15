package com.kaspi.backend.util.response.errorController;

import com.kaspi.backend.dto.error.ErrorResponseDto;
import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.exception.DefaultException;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {


    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorResponseDto> defaultExceptionHandler(DefaultException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponseDto.toResponse(e));
    }
}
