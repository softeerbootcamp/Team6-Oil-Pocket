package com.kaspi.backend;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisConnectionTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @DisplayName("redis 연동 및 template 함수 테스트")
    void testRedis() {
        //given
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        //when
        valueOperations.set("keyTest","valueTest");
        //then
        String value = valueOperations.get("keyTest");
        Assertions.assertThat(value).isEqualTo("valueTest");
    }

}