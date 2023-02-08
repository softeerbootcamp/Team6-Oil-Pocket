package com.kaspi.backend.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@SpringBootTest
class GasStationDataDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("address 조회 테스트")
    void test1() {
        GasStationDataDao gasStationDataDao = new GasStationDataDao(jdbcTemplate);
        List<String> list = gasStationDataDao.selectAllGasStation();
        HashSet<String> hashSet1 = new HashSet<>();
        List<String> countList = new ArrayList<>();
        int count = 0;
        for (String s : list) {
            hashSet1.add(s);
            countList.add(s);
        }
        for (String s : hashSet1) {
            if (Collections.frequency(countList, s) > 1) {
                count++;
                System.out.println(s);
            }
        }
        System.out.println(count);
    }

    @Test
    @DisplayName("해쉬셋 테스트")
    void test2() {
        HashSet<String> set = new HashSet<>();
        String str = "1";
        set.add(str);
        String str2 = "1";
        set.add(str);
        System.out.println(set.size());
    }
}