package com.kaspi.backend.dao;

import static org.assertj.core.api.Assertions.*;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;

import java.util.Optional;

import com.kaspi.backend.util.config.RedisConfiguration;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

//TODO 내장 redis 연동 후 다시 테스트
@DataJdbcTest
@ContextConfiguration(classes = {RedisConfiguration.class, TestRedisConfiguration.class})
class UserDaoTest {


    @Autowired
    private UserDao userDao;


    @Test
    @DisplayName("사용자 아이디를 조건으로 user존재하는지 쿼리 테스트")
    void findByUserId() {
        //given
        String userId = "test";
        User existUser = User.builder()
                .id(userId)
                .password("password")
                .gender(Gender.MALE)
                .age(Age.TWENTY)
                .build();
        userDao.save(existUser);
        //when
        Optional<User> findUser = userDao.findByUserId(userId);
        //then
        assertThat(findUser.isPresent()).isTrue();
        assertThat(findUser.get()).usingRecursiveComparison().isEqualTo(existUser);
    }
}