package com.kaspi.backend.controller;

import static com.google.common.net.HttpHeaders.SET_COOKIE;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.dto.UserUpdateReqDto;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSessionService httpSessionService;

    @PostMapping("/v1/user")
    public ResponseEntity<CommonResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User makeUser = userService.makeUser(signUpRequestDto);
        httpSessionService.makeHttpSession(makeUser.getUserNo());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_SIGNUP));
    }

    @PatchMapping("/v2/user")
    public ResponseEntity<CommonResponseDto> userUpdate(@RequestBody UserUpdateReqDto userUpdateReqDto) {
        userService.updateUser(userUpdateReqDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_FIX_USER));
    }


}
