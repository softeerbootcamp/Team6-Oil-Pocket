package com.kaspi.backend.dao;

import static org.assertj.core.api.Assertions.*;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


@DataJdbcTest
@ContextConfiguration(classes = {TestRedisConfiguration.class})
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

    @Test
    @DisplayName("사용자의 아이디를 성별과 age로 받아오는 쿼리 작성")
    void findUserNoByGenderAndAge() {
        User user2 = User.builder().age(Age.FORTY).gender(Gender.MALE).id("admin2").password("password")
                        .build();
        User user3 = User.builder().age(Age.FORTY).gender(Gender.MALE).id("admin3").password("password")
                .build();
        User user4 = User.builder().age(Age.FORTY).gender(Gender.FEMALE).id("admin4").password("password")
                .build();
        User user5 = User.builder().age(Age.TWENTY).gender(Gender.MALE).id("admin5").password("password")
                .build();
        User user6 = User.builder().age(Age.FIFTY).gender(Gender.MALE).id("admin6").password("password")
                .build();
        userDao.saveAll(Arrays.asList(user2, user3, user4, user5, user6));
        Optional<List<Long>> optionalUserNoList = userDao.findUserNoByGenderAndAge(Gender.MALE, Age.FORTY);
        List<Long> userNoList = optionalUserNoList.get();

        Assertions.assertThat(userNoList.size()).isEqualTo(3);
    }
}