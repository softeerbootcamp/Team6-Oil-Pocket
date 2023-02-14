package com.kaspi.backend.dao;

import com.kaspi.backend.domain.EcoRecord;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJdbcTest
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserGasRecordDaoTest {
    @Autowired
    UserGasRecordDao userGasRecordDao;
    @Test
    @DisplayName("현재 날짜가 해당된 달의 유저가스레코드를 가져오는 성공 테스트")
    void findByMonthOfNow_SUCCESS() {
        Optional<List<UserGasRecord>> optionalUserGasRecordList = userGasRecordDao.findByMonthOfNow(1L, LocalDate.now());
        SoftAssertions softly = new SoftAssertions();
        for (UserGasRecord userGasRecord : optionalUserGasRecordList.get()) {
            //2월 안에 드는지 테스트
            softly.assertThat(userGasRecord.getChargeDate()).isBetween("2023-02-01", "2023-02-28");
        }
        softly.assertAll();
    }

    @Test
    @DisplayName("유저 절약 정보 조회 테스트")
    void findSavingPriceByGenderAndAge_SUCCESS() {
        List<EcoRecord> ecoRecords = userGasRecordDao.findSavingPriceByGenderAndAge(Gender.MALE, Age.FORTY, LocalDate.now()).get();
        Assertions.assertThat(ecoRecords.size()).isEqualTo(1);

    }

}