package com.kaspi.backend.controller;


import com.kaspi.backend.service.AuthService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/v1/auth")
    public ResponseEntity<CommonResponseDto> checkDuplicateId(@RequestParam("id") String id) {
        if (!authService.checkValidUserId(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(CommonResponseDto.toResponse(ErrorCode.DUPLICATE_USER));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_VALID_ID));
    }
}
