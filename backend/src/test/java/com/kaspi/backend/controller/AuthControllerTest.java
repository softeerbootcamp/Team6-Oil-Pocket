package com.kaspi.backend.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignInRequestDto;
import com.kaspi.backend.service.AuthService;

import java.util.HashMap;
import java.util.Map;

import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import com.kaspi.backend.util.response.code.DefaultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = AuthController.class)
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    HttpSessionService httpSessionService;


    @Test
    @DisplayName("요청 ID가 사용가능 ID인경우 컨트롤러 테스트")
    void checkDuplicateId_whenUserExists_returnsConflict() throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("id", "existing_user_id");
        when(authService.checkValidUserId(param.get("id"))).thenReturn(true);
        mockMvc.perform(get("/api/v1/auth").param("id", param.get("id")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(DefaultCode.SUCCESS_VALID_ID.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.SUCCESS_VALID_ID.getMessage()));
    }

    @Test
    @DisplayName("로그인 API 테스트")
    public void signIn() throws Exception {
        //given
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().id("test").password("password").build();
        User authenticatedUser = User.builder().userNo(1L).build();
        given(authService.signIn(signInRequestDto)).willReturn(authenticatedUser);
        //wheb
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(signInRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("code").value(DefaultCode.SUCCESS_SIGN_IN.getCode()));

        verify(authService).signIn(signInRequestDto);
        verify(httpSessionService).makeHttpSession(authenticatedUser.getUserNo());
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}