package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.enums.Gender;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}