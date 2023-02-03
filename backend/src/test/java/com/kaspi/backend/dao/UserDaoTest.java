package com.kaspi.backend.dao;

import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @DisplayName("유저 insert,select query 테스트")
    public void testInsertUser() {
        //given
        User user = new User.builder().userNo(1L).age(Age.TWENTY).gender(Gender.MALE).id("test").password("testpwd").build();
        //when
        userDao.insertUser(user.getId(), user.getPassword(), user.getGender().name(), user.getAge().name());
        //then
        User findUser = userDao.findByUserNo(user.getUserNo()).get();
        assertThat(findUser).usingRecursiveComparison().isEqualTo(user);
    }
}