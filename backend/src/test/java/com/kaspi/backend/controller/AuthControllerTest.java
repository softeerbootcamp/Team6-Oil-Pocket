package com.kaspi.backend.controller;

import static org.mockito.Mockito.when;

import com.kaspi.backend.service.AuthService;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = AuthController.class,
        excludeAutoConfiguration = {
                RedisAutoConfiguration.class,
                RedisRepositoriesAutoConfiguration.class,
                RedisHttpSessionConfiguration.class,
                RedisConnectionFactory.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
//@Import(RedisTestConfig.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AuthService authService;


    @Test
    @DisplayName("요청 ID가 이미사용중인  ID인경우 컨트롤러 테스트")
    void checkDuplicateId_whenUserExists_returnsConflict() throws Exception {
        Map<String, String> param = new HashMap<>();
        param.put("id", "existing_user_id");
        when(authService.checkValidUserId(param.get("id"))).thenReturn(false);
        mockMvc.perform(get("/v1/auth").param("id", param.get("id")))
                .andExpect(status().isConflict());
    }
}