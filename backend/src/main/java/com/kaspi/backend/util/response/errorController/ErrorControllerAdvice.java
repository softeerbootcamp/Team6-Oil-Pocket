package com.kaspi.backend.util.response.errorController;

import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.exception.AuthorizationException;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CommonResponseDto> AuthenticationHandler(AuthenticationException e) {
        log.error(e.getCode().getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponseDto.toResponse(e.getCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponseDto> IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponseDto.toResponse(ErrorCode.PARAMETER_ERROR));
    }

//    @ExceptionHandler(AuthorizationException.class)
//    public ResponseEntity<CommonResponseDto> AuthorizationHandler(AuthorizationException e) {
//        log.error(e.getCode().getMessage());
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponseDto.toResponse(e.getCode()));
//    }
}
