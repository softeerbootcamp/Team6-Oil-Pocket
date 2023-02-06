package com.kaspi.backend.util.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import redis.embedded.RedisServer;

@Configuration
@EnableRedisRepositories
@Import(RedisHttpSessionConfiguration.class)
public class RedisTestConfig {
    private RedisServer redisServer;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost",6379);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @BeforeEach
    public void setUp() {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterEach
    public void tearDown() {
        redisServer.stop();
    }
}

