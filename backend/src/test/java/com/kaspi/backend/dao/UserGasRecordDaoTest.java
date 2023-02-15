package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.dto.UserGasRecordMonthDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import java.time.LocalDate;
import com.kaspi.backend.domain.EcoRecord;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    @Autowired
    UserDao userDao;
    @Autowired
    GasStationDao gasStationDao;

    @Test
    @DisplayName("특정 유저의 주유기록 데이터 select")
    void findGasRecordListByUserId() {
        //given
        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        GasStation gasStation = GasStation.parseGasStation(input);
        GasStation saveGasStation = gasStationDao.save(gasStation);

        User existUser = User.builder()
                .id("test")
                .password("password")
                .gender(Gender.MALE)
                .age(Age.TWENTY)
                .build();
        User saveUser = userDao.save(existUser);

        UserGasRecord existGasRecord = UserGasRecord.builder()
                .userNo(saveUser.getUserNo())
                .gasStationNo(saveGasStation.getStationNo())
                .recordGasAmount(2L)
                .recordGasType(GasType.GASOLINE)
                .refuelingPrice(10000L)
                .chargeDate(LocalDate.now())
                .savingPrice(123L)
                .build();

        UserGasRecord savedUserGasRecord = userGasRecordDao.save(existGasRecord);
        //when
        List<UserGasRecord> gasRecordListByUserId = userGasRecordDao.findGasRecordListByUserId(saveUser.getUserNo());
        //then
        UserGasRecord actualGasRecord = gasRecordListByUserId.get(0);
        assertThat(actualGasRecord.getGasStationNo()).isEqualTo(savedUserGasRecord.getGasStationNo());
        assertThat(actualGasRecord.getRecordGasType()).isEqualTo(savedUserGasRecord.getRecordGasType());
        assertThat(actualGasRecord.getRecordGasAmount()).isEqualTo(savedUserGasRecord.getRecordGasAmount());
        assertThat(actualGasRecord.getRefuelingPrice()).isEqualTo(savedUserGasRecord.getRefuelingPrice());
        assertThat(actualGasRecord.getSavingPrice()).isEqualTo(savedUserGasRecord.getSavingPrice());
    }

    @Test
    @DisplayName("현재 날짜가 해당된 달의 유저가스레코드를 가져오는 성공 테스트")
    void findByMonthOfNow_SUCCESS() {
        Optional<List<UserGasRecord>> optionalUserGasRecordList = userGasRecordDao.findByMonthOfNow(1L,
                LocalDate.now());
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
        List<EcoRecord> ecoRecords = userGasRecordDao.findSavingPriceByGenderAndAge(Gender.MALE, Age.FORTY,
                LocalDate.now()).get();
        assertThat(ecoRecords.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("주유기록 월별 합계(주유금액, 국내 평균 주유금액)")
    void findSumRecordGroupByMonth() {
        //given
        User user = User.builder().id("test").password("test").age(Age.TWENTY).gender(Gender.MALE).build();
        User savedUser = userDao.save(user);
        saveUserRecordPerMonth(savedUser);
        //when
        Optional<List<UserGasRecordMonthDto>> sumRecordGroupByMonth = userGasRecordDao.findSumRecordGroupByMonth(
                user.getUserNo());
        //then
        List<UserGasRecordMonthDto> userGasRecordMonthDtos = sumRecordGroupByMonth.get();
        for (int i = 0; i < 12; i++) {
            assertThat(userGasRecordMonthDtos.get(i).getMonthDate()).isEqualTo(LocalDate.now().minusMonths(i).format(
                    DateTimeFormatter.ofPattern("yyyy.MM")));
            assertThat(userGasRecordMonthDtos.get(i).getTotalRefuelingPrice()).isEqualTo((i * 1000));
            assertThat(userGasRecordMonthDtos.get(i).getTotalSavingPrice()).isEqualTo((i * 10));
        }
    }

    private void saveUserRecordPerMonth(User user) {
        List<UserGasRecord> userGasRecordList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            userGasRecordList.add(
                    UserGasRecord.builder()
                            .userNo(user.getUserNo())
                            .gasStationNo(1L)
                            .recordGasType(GasType.GASOLINE)
                            .recordGasAmount(10L)
                            .chargeDate(LocalDate.now().minusMonths(i))
                            .refuelingPrice((long) (i * 1000))
                            .savingPrice((long) (i * 10)).build());
        }
        userGasRecordDao.saveAll(userGasRecordList);
    }
}