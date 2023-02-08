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
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


@DataJdbcTest
@ContextConfiguration(classes = {RedisConfiguration.class, TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserDaoTest {
    @Autowired
    UserDao userDao;


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