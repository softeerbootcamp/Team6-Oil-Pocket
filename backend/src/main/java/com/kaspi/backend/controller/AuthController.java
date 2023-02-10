package com.kaspi.backend.controller;


import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.service.AuthService;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final HttpSessionService httpSessionService;

    @GetMapping("/v1/auth")
    public ResponseEntity<CommonResponseDto> checkDuplicateId(@RequestParam("id") String id) {
        if (!authService.checkValidUserId(id)) {
            log.error("아이디가 중복된 유저: {}",id);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(CommonResponseDto.toResponse(ErrorCode.DUPLICATE_USER));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_VALID_ID));
    }

    @PostMapping("/v1/auth")
    public ResponseEntity<CommonResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {

        User authenticatedUser = authService.signIn(signInRequestDto);
        httpSessionService.makeHttpSession(authenticatedUser.getUserNo());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_SIGN_IN));
    }
}
