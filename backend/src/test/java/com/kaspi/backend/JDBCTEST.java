package com.kaspi.backend;

import com.kaspi.backend.test.TestEntity;
import com.kaspi.backend.test.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JDBCTEST {
    @Autowired
    TestRepository testRepository;

    @Test
    void dbConnectionTest() {
        //given
        TestEntity test = new TestEntity("test");
        //when
        testRepository.save(test);
        //then


    }

}