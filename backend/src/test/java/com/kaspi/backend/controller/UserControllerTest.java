package com.kaspi.backend.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.dto.UserUpdateReqDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.service.AuthService;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import com.kaspi.backend.util.response.code.DefaultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;
    @MockBean
    HttpSessionService httpSessionService;

    @Test
    @DisplayName("회원가입 api 성공 테스트")
    void testSignUp_SUCCESS() throws Exception {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder().id("test").password("password").age("20대").gender("M").build();
        User makeUser = User.builder().id("test").password("password").gender(Gender.MALE).age(Age.TWENTY).build();
        // when
        given(userService.makeUser(signUpRequestDto)).willReturn(makeUser);
        //then
        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(signUpRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("code").value(DefaultCode.SUCCESS_SIGNUP.getCode()))
                .andExpect(jsonPath("message").value(DefaultCode.SUCCESS_SIGNUP.getMessage()));

        verify(userService).makeUser(signUpRequestDto);
        verify(httpSessionService).makeHttpSession(makeUser.getUserNo());
    }


    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("유저 정보 수정 API 테스트")
    void userUpdate() throws Exception {
        UserUpdateReqDto dto = UserUpdateReqDto.builder().age(Age.FORTY).gender(Gender.MALE).build();

        doNothing().when(userService).updateUser(dto);

        mockMvc.perform(patch("/api/v2/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(dto)))
                .andExpect(status().isOk());

    }
}