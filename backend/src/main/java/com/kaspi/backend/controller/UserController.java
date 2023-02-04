package com.kaspi.backend.controller;

import static com.google.common.net.HttpHeaders.SET_COOKIE;

import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<CommonResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto,
                                                    HttpSession session) {
        Long userNo = userService.makeUser(signUpRequestDto);
        ResponseCookie responseSessionCookie = userService.makeCookieFromSession(userNo, session);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(SET_COOKIE, responseSessionCookie.toString())
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_SIGNUP));
    }


}
