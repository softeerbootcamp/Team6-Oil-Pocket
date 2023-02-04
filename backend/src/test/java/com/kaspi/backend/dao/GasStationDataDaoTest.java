package com.kaspi.backend.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
            String[] temp = s.split(" ");
            int idx = 0;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].contains("로")) {
                    idx = i;
                    break;
                }
            }
            String result = "";
            for (int i = idx; i < temp.length; i++) {
                result += temp[i] + " ";
            }
            hashSet1.add(result);
            countList.add(result);
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